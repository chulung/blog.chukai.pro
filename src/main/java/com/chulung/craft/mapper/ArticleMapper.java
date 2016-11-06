package com.chulung.craft.mapper;

import java.util.List;

import com.chulung.craft.dto.ArticleDto;
import com.chulung.craft.model.Article;

public interface ArticleMapper extends BaseMapper<Article>{
	List<Article> selectTileList();
	List<Article> selectListForMetaClblog(String siteName);
	List<Article> selectBySelectiveForBlog(ArticleDto record);
	int incrementVisitCount(Integer articleId);

}