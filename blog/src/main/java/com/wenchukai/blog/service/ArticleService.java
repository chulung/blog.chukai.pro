package com.wenchukai.blog.service;

import java.util.List;

import com.wenchukai.blog.bean.Article;
import com.wenchukai.blog.bean.ArticleDraft;
import com.wenchukai.blog.bean.ArticleType;
import com.wenchukai.durable.bean.PageIn;

/**
 * 
 * @author ChuKai
 *
 */
public interface ArticleService {
	Article findArticleById(Integer id);

	List<Article> findArticlesListByAjax(PageIn pageIn);

	boolean update(ArticleDraft articleDraft);

	List<ArticleType> findAllArticleTypes();

	Integer findArticleDraftIdByArticleId(ArticleDraft article);

	void insert(ArticleDraft articleDraft);

	List<ArticleDraft> findArticleDraftsListByAjax(PageIn pageIn);

	ArticleDraft findArticleDraft(ArticleDraft articleDraft);

	void deleteArticleDraft(Integer id);
}
