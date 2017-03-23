package com.chulung.website.service;

import java.util.List;

import com.chulung.website.dto.ArticleFiling;
import com.chulung.website.dto.PageIn;
import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.model.Article;
import com.chulung.website.model.ArticleDraft;
import com.chulung.website.dto.out.PageOut;

/**
 * @author chulung
 */
public interface ArticleService {
    void deleteArticleDraft(Integer id);

    Article findArticleById(Integer id);

    List<ArticleOut> findRecommendedArticles();

    List<Article> findPopularArticles();

    List<Article> findRelevancyByArticleId(Integer id);

    List<ArticleDraft> findArticleDraftsList(PageIn<ArticleDraft> pageIn);

    ArticleDraft findArticleDraft(Integer id);

    Integer insert(ArticleDraft articleDraft);

    boolean update(ArticleDraft articleDraft);
    List<ArticleFiling> getArticleFilings();

    List<Article> getArticlesByTagName(String tagName);

    PageOut<ArticleOut> findArticlePage(Integer page, String column, Integer yearMonth, Integer month);
}
