package com.chulung.blog.mapper;

import com.chulung.blog.model.ArticleDraftHistory;
import com.chulung.ckbatis.mapper.BaseMapper;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory>{
    int insertToArticleDraftHistory(Integer id);
}