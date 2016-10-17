package com.chulung.blog.mapper;

import com.chulung.blog.model.ArticleDraftHistory;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory>{
    int insertToArticleDraftHistory(Integer id);
}