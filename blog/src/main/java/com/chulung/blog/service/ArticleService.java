package com.chulung.blog.service;

import java.util.List;
import java.util.Optional;

import com.chulung.blog.dto.CommonInfo;
import com.chulung.blog.dto.TreeNode;
import com.chulung.blog.model.Article;
import com.chulung.blog.model.ArticleDraft;
import com.chulung.blog.model.Dictionary;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author ChuKai
 *
 */
public interface ArticleService {
	Article findArticleById(Integer id);

	boolean update(ArticleDraft articleDraft);

	List<Dictionary> findAllArticleTypes();

	void insert(ArticleDraft articleDraft);

	ArticleDraft findArticleDraft(Integer id);

	void deleteArticleDraft(Integer id);

	List<TreeNode> getCategoryTreeNode();

	/**
	 * 查询最新的博客,用于首页显示
	 * 
	 * @param page
	 * @param articleType
	 * @return
	 */
	PageInfo<Article> selectBySelectiveForBlog(Optional<Integer> page, Integer typeId);

	CommonInfo getCommonInfo();

	List<Article> getBlogsByYearMonth(Integer year, Integer month);
}
