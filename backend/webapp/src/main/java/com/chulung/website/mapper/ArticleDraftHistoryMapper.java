package com.chulung.website.mapper;

import com.chulung.mybatis.mapper.BaseMapper;
import com.chulung.website.model.ArticleDraftHistory;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory> {
    int insertToArticleDraftHistory(Integer id);
}