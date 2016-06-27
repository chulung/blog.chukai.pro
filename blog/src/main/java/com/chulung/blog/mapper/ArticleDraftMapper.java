package com.chulung.blog.mapper;

import java.util.List;

import com.chulung.blog.model.ArticleDraft;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft>{
	List<ArticleDraft> selectTileList(ArticleDraft record);
}