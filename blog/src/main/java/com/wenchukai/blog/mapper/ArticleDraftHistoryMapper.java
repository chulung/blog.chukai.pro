package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.ArticleDraftHistory;
import com.wenchukai.ckbatis.mapper.BaseMapper;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory>{
    int insertToArticleDraftHistory(Integer id);
}