package com.wchukai.web.service;

import com.wchukai.web.dto.out.Archive;
import com.wchukai.web.dto.out.ArticleDraftOut;
import com.wchukai.web.dto.out.ArticleOut;
import com.wchukai.web.dto.out.PageOut;
import com.wchukai.web.model.Article;
import com.wchukai.web.model.ArticleDraft;

import java.util.List;

/**
 * @author wchukai
 */
public interface ArticleService {
    void deleteArticleDraft(Integer id);

    Article findArticleByUri(String uri);

    List<ArticleOut> findRecommendedArticles();

    List<Article> recentUpdateArticles();

    List<Article> findRelevancyByArticleId(Integer id);

    PageOut<ArticleDraftOut> findArticleDraftsList(Integer pageIn, Integer columnId);

    ArticleDraftOut findArticleDraft(Integer id);

    Integer insert(ArticleDraft articleDraft);

    Article findArticleById(Integer id);

    boolean update(ArticleDraft articleDraft);

    List<Archive> getArchive();

    PageOut<ArticleOut> getArticlesByTagName(String tagName);

    PageOut<ArticleOut> findArticlePage(Integer page, String column, Integer yearMonth, Integer month);
}
