package com.wenchukai.blog.mapper;

import java.util.List;

import com.wenchukai.blog.model.ArticleDraft;
import com.wenchukai.ckbatis.mapper.BaseMapper;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft>{
	List<ArticleDraft> selectTileList();
}