package com.chulung.website.mapper;

import com.chulung.website.model.ArticleDraftHistory;
import com.chulung.mybatis.mapper.BaseMapper;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory> {
    int insertToArticleDraftHistory(Integer id);
}