package com.chulung.blog.mapper;

import java.util.List;

import com.chulung.blog.model.Article;
import com.chulung.ckbatis.mapper.BaseMapper;

public interface ArticleMapper extends BaseMapper<Article>{
	List<Article> selectTileList();
	List<Article> selectBySelectiveForBlog(Article record);
	int incrementVisitCount(Integer articleId);

}