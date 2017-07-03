package com.chulung.search;

import com.chulung.search.core.Search;
import com.chulung.search.core.SearchDocument;
import com.chulung.website.dto.in.ArticleIn;
import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.mapper.ArticleMapper;
import com.chulung.website.model.Article;
import com.chulung.website.model.BaseComponent;
import com.chulung.website.service.ConfigService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 封装网站搜索与索引功能
 * Created by chulung on 2016/11/10.
 */
@Component
@ConfigurationProperties(prefix = "search")
public class ArticlesSearchHandler extends BaseComponent implements ApplicationListener<ContextRefreshedEvent> {

    private boolean lazy;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private Search search;

    @Autowired
    private ConfigService configService;

    private void resetIndex() {
        logger.info("开始重建索引......");
        search.clearAll();
        indexAll();
        logger.info("重建索引完毕......");
    }

    /**
     * 索引所有文章
     */
    private void indexAll() {
        List<Article> list;
        int pageNum = 1;
        PageHelper.startPage(pageNum, 10);
        while (!(list = this.articleMapper.selectAll()).isEmpty()) {
            search.createIndex(list.parallelStream().map(article -> new SearchDocument(article.getId().toString(), article.getTitle(), replaceHtmlTag(article))
            ).collect(Collectors.toList()));
            PageHelper.startPage(++pageNum, 10);
        }
    }

    private String replaceHtmlTag(Article article) {
        return article.getContent().replaceAll("</?[^<]+>", "").replaceAll("\\s+", "");
    }

    /**
     * 索引文章
     *
     * @param articleId articleId
     */
    public void index(Integer articleId) {
        checkLazyLoad();
        Article article = this.articleMapper.selectByPrimaryKey(articleId);
        if (article == null) return;
        this.search.createIndex(new SearchDocument(article.getId().toString(), article.getTitle(), replaceHtmlTag(article)));
    }

    public PageOut<ArticleOut> search(String key) {
        checkLazyLoad();
        try {
            List<SearchDocument> search = this.search.search(key);
            ArticleIn record = new ArticleIn();
            record.setIds(search.stream().map(a -> Integer.parseInt(a.getId())).collect(Collectors.toList()));
            Map<Integer, Article> map = this.articleMapper.selectSummarys(record).stream().collect(Collectors.toMap(Article::getId, Function.identity()));
            List<ArticleOut> list = search.stream().map(cSearchDocument -> {
                ArticleOut article = new ArticleOut();
                article.setId(Integer.parseInt(cSearchDocument.getId()));
                article.setTitle(cSearchDocument.getTitle());
                article.setSummary(cSearchDocument.getContent());
                Article ext = map.get(article.getId());
                article.setCreateTime(ext.getCreateTime());
                article.setUri(ext.getUri());
                article.setAuthor(ext.getAuthor());
                article.setColumnName(ext.getColumnName());
                article.setColumnId(ext.getColumnId());
                article.setVisitCount(ext.getVisitCount());
                return new ArticleOut().buildFromModel(article);
            }).collect(Collectors.toList());
            return new PageOut<ArticleOut>(1, list.size(), list);
        } catch (Exception e) {
            errorLog(e);
            return PageOut.EMPTY;
        }
    }

    private void checkLazyLoad() {
        if (lazy) {
            resetIndex();
            lazy = false;
        }
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (lazy) return;
        new Thread(() -> {
            this.resetIndex();
        }).start();
    }
}
