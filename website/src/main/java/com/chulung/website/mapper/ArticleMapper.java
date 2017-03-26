package com.chulung.website.mapper;

import java.util.List;

import com.chulung.website.dto.in.ArticleIn;
import com.chulung.website.model.Article;
import com.chulung.mybatis.mapper.BaseMapper;

public interface ArticleMapper extends BaseMapper<Article> {

	List<Article> selectListForMetaClblog(String siteName);
	List<Article> selectSummarys(ArticleIn record);

	/**
	 * 自增文章点击次数
	 * @param articleId
	 * @return
	 */
	int incrementVisitCount(Integer articleId);

	/**
	 * 查询热门文章
	 * @return
	 */
    List<Article> listPopularArticles();

	/**
	 *  根据文章id查询关联文章 标签相同
	 * @param articleId
	 * @return
	 */
	List<Article> listRelevancy(Integer articleId);

}