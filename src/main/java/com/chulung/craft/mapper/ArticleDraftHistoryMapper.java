package com.chulung.craft.mapper;

import com.chulung.craft.model.ArticleDraftHistory;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory>{
    int insertToArticleDraftHistory(Integer id);
}