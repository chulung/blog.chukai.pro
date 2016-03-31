package com.wenchukai.blog.mapper;

import java.util.List;

import com.wenchukai.blog.model.Article;

public interface ArticleMapper {
	Integer insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	List<Article> selectAll();

	int updateByPrimaryKeySelective(Article record);

	List<Article> selectTileList();

	List<Article> selectBySelective(Article record);

	List<Article> selectBySelectiveForBlog(Article record);

}