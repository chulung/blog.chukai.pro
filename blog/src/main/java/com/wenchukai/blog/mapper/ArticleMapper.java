package com.wenchukai.blog.mapper;

import java.util.List;

import com.wenchukai.blog.model.Article;
import com.wenchukai.ckbatis.mapper.BaseMapper;

public interface ArticleMapper extends BaseMapper<Article>{
	List<Article> selectTileList();
	List<Article> selectBySelectiveForBlog(Article record);
	int incrementVisitCount(Integer articleId);

}