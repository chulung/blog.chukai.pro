package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleMapper {
	Integer insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	List<Article> selectAll();

	int updateByPrimaryKeySelective(Article record);

	List<Article> selectTileList();

	List<Article> selectBySelective(Article record);

	List<Article> selectBySelectiveForBlog(Article record);

}