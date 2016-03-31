package com.wenchukai.blog.mapper;

import java.util.List;

import com.wenchukai.blog.model.ArticleDraft;

public interface ArticleDraftMapper {
	Integer insertSelective(ArticleDraft record);

	List<ArticleDraft> selectAll();

	int updateByPrimaryKeySelective(ArticleDraft record);

	ArticleDraft selectByPrimaryKey(Integer id);
	List<ArticleDraft> selectBySelective(ArticleDraft articleDraft);

	List<ArticleDraft> selectTileList();
}