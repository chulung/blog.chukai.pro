package com.wchukai.web.mapper;

import com.wchukai.mybatis.mapper.BaseMapper;
import com.wchukai.web.model.ArticleDraft;

import java.util.List;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft> {
    List<ArticleDraft> selectTileList(ArticleDraft record);
}