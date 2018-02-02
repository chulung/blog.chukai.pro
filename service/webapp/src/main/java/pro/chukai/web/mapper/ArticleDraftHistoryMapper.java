package pro.chukai.web.mapper;

import pro.chukai.mybatis.mapper.BaseMapper;
import pro.chukai.web.model.ArticleDraftHistory;
import pro.chukai.mybatis.mapper.BaseMapper;

public interface ArticleDraftHistoryMapper extends BaseMapper<ArticleDraftHistory> {
    int insertToArticleDraftHistory(Integer id);
}