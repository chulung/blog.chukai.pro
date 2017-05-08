package com.chulung.website.quartz.job;

import com.chulung.website.constant.Constants;
import com.chulung.website.mapper.ArticleMapper;
import com.chulung.website.model.Article;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chulung.website.mapper.CommentMapper;

import java.time.ZoneId;
import java.util.List;

@Component
public class CommonCronJob extends AbstractCronJob {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentsMapper;

    @Scheduled(cron = "0 0 1 * * ?")
    public void execute() {
        List<Article> articles = articleMapper.selectAll();
        // 重新计算评论数
        recalcCommentsCount(articles);
        //重新计算热门排行

        recalcIndexRank(articles);
    }

    private void recalcIndexRank(List<Article> articles) {
        double[] timePoint = new double[articles.size()];
        double[] commentsPoint = new double[articles.size()];
        double[] visitorCountPoint = new double[articles.size()];
        for (int i = 0; i < articles.size(); i++) {
            timePoint[i] = articles.get(i).getCreateTime().atZone(ZoneId.systemDefault()).toEpochSecond() - Constants.FIRST_ARTICLE_CREATE_TIME;
            commentsPoint[i] = articles.get(i).getCommentCount();
            visitorCountPoint[i] = articles.get(i).getVisitCount();
        }
        normalization(timePoint);
        normalization(commentsPoint);
        normalization(visitorCountPoint);
        for (int i = 0; i < articles.size(); i++) {
            Article r = new Article();
            r.setId(articles.get(i).getId());
            r.setIndexRank((int) ((timePoint[i] + commentsPoint[i] + visitorCountPoint[i]) * 1000));
            articleMapper.updateByPrimaryKeySelective(r);
            ;
        }
    }

    private void recalcCommentsCount(List<Article> articles) {
        articles.parallelStream().forEach(e -> {
            commentsMapper.recalcCommentsCountForArticle(e.getId());
        });
    }

    /**
     * 线性函数归一化(Min-Max scaling)
     *
     * @param arr 将arr归一化
     */
    private void normalization(double[] arr) {
        if (ArrayUtils.isEmpty(arr)) return;
        double max = arr[0];
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
            min = arr[i] < min ? arr[i] : min;
        }
        double v = max - min;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] - min) / v;
        }
    }
}
