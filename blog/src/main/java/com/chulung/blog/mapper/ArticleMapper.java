package com.chulung.blog.mapper;

import java.util.List;

import com.chulung.blog.dto.ArticleDto;
import com.chulung.blog.model.Article;

public interface ArticleMapper extends BaseMapper<Article>{
	List<Article> selectTileList();
	List<Article> selectBySelectiveForBlog(ArticleDto record);
	int incrementVisitCount(Integer articleId);

}