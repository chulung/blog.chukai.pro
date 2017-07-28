package com.chulung.website.mapper;

import com.chulung.mybatis.mapper.BaseMapper;
import com.chulung.website.model.ArticleTag;

import java.util.List;

/**
 * Created by chulung on 2017/2/18.
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<ArticleTag> selectAllTags();
}
