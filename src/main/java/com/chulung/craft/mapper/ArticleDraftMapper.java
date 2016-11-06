package com.chulung.craft.mapper;

import java.util.List;

import com.chulung.craft.model.ArticleDraft;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft>{
	List<ArticleDraft> selectTileList(ArticleDraft record);
}