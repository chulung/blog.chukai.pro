package com.chulung.craft.quartz.job;

import com.chulung.common.util.NumberUtil;
import com.chulung.craft.constant.Constants;
import com.chulung.craft.mapper.ArticleMapper;
import com.chulung.craft.model.Article;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chulung.craft.mapper.CommentsMapper;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Component
public class CommonCronJob extends AbstractCronJob {

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private CommentsMapper commentsMapper;

	@Override
	public void execute() throws Exception {
		List<Article> articles = articleMapper.selectAll();
		// 重新计算评论数
		recalcCommentsCount(articles);
		//重新计算热门排行

        recalcIndexRank(articles);
	}

    private void recalcIndexRank(List<Article> articles) {
        double [] timePoint= new double[articles.size()];
        double [] commentsPoint= new double[articles.size()];
        ;
        double [] visitorCountPoint= new double[articles.size()];
        ;
        for (int i=0;i<articles.size();i++){
            timePoint[i]=articles.get(i).getCreateTime().atZone(ZoneId.systemDefault()).toEpochSecond()- Constants.FIRST_ARTICLE_CREATE_TIME;
            commentsPoint[i]=articles.get(i).getCommentCount();
            visitorCountPoint[i]=articles.get(i).getVisitCount();
        }
        timePoint= NumberUtil.normalization(timePoint);
        commentsPoint=NumberUtil.normalization(commentsPoint);
        visitorCountPoint=NumberUtil.normalization( visitorCountPoint);
        for (int i = 0; i < articles.size(); i++) {
            Article r=new Article();
            r.setId(articles.get(i).getId());
            r.setIndexRank((int)((timePoint[i]+commentsPoint[i]+visitorCountPoint[i])*1000));
            articleMapper.updateByPrimaryKeySelective(r);
            ;
        }
    }

    private void recalcCommentsCount(List<Article> articles) {
		articles.parallelStream().forEach(e -> {
			commentsMapper.recalcCommentsCountForArticle(e.getId());
		});
	}

    @Override
    public void afterPropertiesSet() throws Exception {
//        this.mainHandler();
    }
}
