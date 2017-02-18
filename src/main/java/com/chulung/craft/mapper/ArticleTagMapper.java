package com.chulung.craft.mapper;

import com.chulung.craft.model.ArticleTag;

import java.util.List;

/**
 * Created by chukai on 2017/2/18.
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<ArticleTag> selectAllTags();
}
