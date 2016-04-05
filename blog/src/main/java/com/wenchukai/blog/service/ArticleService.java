package com.wenchukai.blog.service;

import java.util.List;

import com.wenchukai.blog.dto.PageIn;
import com.wenchukai.blog.model.Article;
import com.wenchukai.blog.model.ArticleDraft;
import com.wenchukai.blog.model.ArticleType;

/**
 * 
 * @author ChuKai
 *
 */
public interface ArticleService {
	Article findArticleById(Integer id);

	List<Article> findArticleTitleList(PageIn pageIn);

	boolean update(ArticleDraft articleDraft);

	List<ArticleType> findAllArticleTypes();

	Integer findArticleDraftIdByArticleId(ArticleDraft article);

	void insert(ArticleDraft articleDraft);

	List<ArticleDraft> findArticleDraftsListByAjax(PageIn pageIn);

	ArticleDraft findArticleDraft(Integer id);

	void deleteArticleDraft(Integer id);
}
