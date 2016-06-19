package com.chulung.blog.service;

import java.util.List;

import com.chulung.blog.dto.PageIn;
import com.chulung.blog.model.Article;
import com.chulung.blog.model.ArticleDraft;
import com.chulung.blog.model.Dictionary;

/**
 * 
 * @author ChuKai
 *
 */
public interface ArticleService {
	Article findArticleById(Integer id);

	List<Article> findArticleTitleList(PageIn<Article> pageIn);

	boolean update(ArticleDraft articleDraft);

	List<Dictionary> findAllArticleTypes();

	Integer findArticleDraftIdByArticleId(ArticleDraft article);

	void insert(ArticleDraft articleDraft);

	List<ArticleDraft> findArticleDraftsList(PageIn<ArticleDraft> pageIn);

	ArticleDraft findArticleDraft(Integer id);

	void deleteArticleDraft(Integer id);


}
