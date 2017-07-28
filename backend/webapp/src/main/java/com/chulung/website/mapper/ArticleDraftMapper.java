package com.chulung.website.mapper;

import com.chulung.mybatis.mapper.BaseMapper;
import com.chulung.website.model.ArticleDraft;

import java.util.List;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft> {
    List<ArticleDraft> selectTileList(ArticleDraft record);
}