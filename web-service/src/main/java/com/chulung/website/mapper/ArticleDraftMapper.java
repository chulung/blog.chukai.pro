package com.chulung.website.mapper;

import java.util.List;

import com.chulung.website.model.ArticleDraft;
import com.chulung.mybatis.mapper.BaseMapper;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft> {
	List<ArticleDraft> selectTileList(ArticleDraft record);
}