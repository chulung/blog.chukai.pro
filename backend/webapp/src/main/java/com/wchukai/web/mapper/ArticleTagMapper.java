package com.wchukai.web.mapper;

import com.wchukai.mybatis.mapper.BaseMapper;
import com.wchukai.web.model.ArticleTag;

import java.util.List;

/**
 * Created by wchukai on 2017/2/18.
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<ArticleTag> selectAllTags();
}
