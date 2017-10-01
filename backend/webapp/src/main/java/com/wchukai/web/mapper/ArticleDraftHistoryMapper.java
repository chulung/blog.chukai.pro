package com.wchukai.web.mapper;

import com.wchukai.mybatis.mapper.BaseMapper;
import com.wchukai.web.model.ArticleDraftHistory;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory> {
    int insertToArticleDraftHistory(Integer id);
}