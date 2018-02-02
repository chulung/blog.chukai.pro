package pro.chukai.web.mapper;

import pro.chukai.mybatis.mapper.BaseMapper;
import pro.chukai.web.model.ArticleDraft;
import pro.chukai.mybatis.mapper.BaseMapper;

import java.util.List;

public interface ArticleDraftMapper extends BaseMapper<ArticleDraft> {
    List<ArticleDraft> selectTileList(ArticleDraft record);
}