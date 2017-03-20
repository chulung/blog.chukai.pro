package com.chulung.website.service;

import java.util.List;

import com.chulung.website.dto.out.CommonInfo;
import com.chulung.website.dto.in.PageIn;
import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.model.Article;
import com.chulung.website.model.ArticleDraft;
import com.chulung.website.dto.out.PageOut;

/**
 * 
 * @author ChuKai
 *
 */
public interface ArticleService {
	Article findArticleById(Integer id);

	boolean update(ArticleDraft articleDraft);
	Article updateArticle(ArticleDraft articleDraft);
	Integer insert(ArticleDraft articleDraft);

	ArticleDraft findArticleDraft(Integer id);

	void deleteArticleDraft(Integer id);

	/**
	 * 查询最新的博客,用于首页显示
	 *
	 * @return
	 */
	PageOut<ArticleOut> selectBySelectiveForArticle(Integer page, Integer typeId);

	CommonInfo getCommonInfo();

	List<Article> getArticlesByYearMonth(Integer year, Integer month);

	List<ArticleDraft> findArticleDraftsList(PageIn<ArticleDraft> pageIn);

	List<Article> listPopularArticles();

	List<Article> getArticlesByTagName(String tagName);

	List<Article> listRelevancy(Integer id);
}
