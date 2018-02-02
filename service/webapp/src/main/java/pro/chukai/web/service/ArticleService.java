package pro.chukai.web.service;

import pro.chukai.web.dto.out.Archive;
import pro.chukai.web.dto.out.ArticleDraftOut;
import pro.chukai.web.dto.out.ArticleOut;
import pro.chukai.web.dto.out.PageOut;
import pro.chukai.web.model.Article;
import pro.chukai.web.model.ArticleDraft;
import pro.chukai.web.dto.out.ArticleDraftOut;
import pro.chukai.web.model.Article;

import java.util.List;

/**
 * @author chukai
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
