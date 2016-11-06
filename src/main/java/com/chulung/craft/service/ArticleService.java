package com.chulung.craft.service;

import java.util.List;
import java.util.Optional;

import com.chulung.craft.dto.CommonInfo;
import com.chulung.craft.dto.PageIn;
import com.chulung.craft.model.Article;
import com.chulung.craft.model.ArticleDraft;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ChuKai
 *
 */
public interface ArticleService {
	Article findArticleById(Integer id);

	boolean update(ArticleDraft articleDraft);
	Integer insert(ArticleDraft articleDraft);

	ArticleDraft findArticleDraft(Integer id);

	void deleteArticleDraft(Integer id);

	/**
	 * 查询最新的博客,用于首页显示
	 *
	 * @param page
	 * @param articleType
	 * @return
	 */
	PageInfo<Article> selectBySelectiveForArticle(Optional<Integer> ofNullable, Integer typeId);

	CommonInfo getCommonInfo();

	List<Article> getArticlesByYearMonth(Integer year, Integer month);

	List<ArticleDraft> findArticleDraftsList(PageIn<ArticleDraft> pageIn);

	List<Article> listPopularArticles();
}
