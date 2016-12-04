package com.chulung.search;

import com.chulung.craft.enumerate.ConfigKeyEnum;
import com.chulung.craft.model.BaseComponent;
import com.chulung.craft.model.Config;
import com.chulung.craft.service.ConfigService;
import com.chulung.csearch.core.CSearch;
import com.chulung.csearch.core.CSearchDocument;
import com.chulung.craft.mapper.ArticleMapper;
import com.chulung.craft.model.Article;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 封装网站搜索与索引功能
 * Created by chulung on 2016/11/10.
 */
@Component
public class ArticlesSearchHandler extends BaseComponent implements InitializingBean {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CSearch cSearchIndex;

    @Autowired
    private ConfigService configService;

    private void resetIndex() {
        cSearchIndex.clearAll();
        indexAll();
    }

    /**
     * 索引所有文章
     */
    private void indexAll() {
        List<Article> list;
        int pageNum = 1;
        PageHelper.startPage(pageNum, 10);
        while (!(list = this.articleMapper.selectAll()).isEmpty()) {
            cSearchIndex.createIndex(list.parallelStream().map(article -> new CSearchDocument(article.docId(), article.getTitle(), replaceHtmlTag(article))
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
        Article article = this.articleMapper.selectByPrimaryKey(articleId);
        if (article == null) return;
        this.cSearchIndex.createIndex(new CSearchDocument(article.docId(), article.getTitle(), replaceHtmlTag(article)));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            if (!Boolean.FALSE.toString().equals(configService.getValueBykey(ConfigKeyEnum.RESET_SEARCH_INDEX, Boolean.TRUE.toString()))) {
                logger.info("开始重建索引......");
                this.resetIndex();
                this.configService.updateByKey(new Config(ConfigKeyEnum.RESET_SEARCH_INDEX, Boolean.FALSE.toString()));
                logger.info("重建索引完毕......");
            }
        }).start();
    }

    public List<Article> search(String key) {
        try {

            return this.cSearchIndex.search(key).stream().map(cSearchDocument -> {
                Article article = new Article();
                article.setIdFromDocId(cSearchDocument.getId());
                article.setTitle(cSearchDocument.getTitle());
                article.setContent(cSearchDocument.getContent());
                return article;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            errorLog(e);
            return Collections.emptyList();
        }
    }
}
