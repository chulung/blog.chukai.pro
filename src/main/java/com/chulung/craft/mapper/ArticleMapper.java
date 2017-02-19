package com.chulung.craft.mapper;

import java.util.List;

import com.chulung.craft.dto.ArticleDto;
import com.chulung.craft.model.Article;

public interface ArticleMapper extends BaseMapper<Article>{
	List<Article> selectTileList();
	List<Article> selectListForMetaClblog(String siteName);
	List<Article> selectSummarys(ArticleDto record);

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