package com.chulung.blog.mapper;

import java.util.List;

import com.chulung.blog.model.ArticleDraft;
import com.chulung.ckbatis.mapper.BaseMapper;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft>{
	List<ArticleDraft> selectTileList();
}