package com.wck.blog.service;

import java.util.List;

import com.wck.blog.bean.Article;
import com.wck.blog.bean.ArticleDraft;
import com.wck.blog.bean.ArticleType;
import com.wck.durable.bean.PageIn;

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
