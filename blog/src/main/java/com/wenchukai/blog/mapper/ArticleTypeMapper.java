package com.wenchukai.blog.mapper;

import java.util.List;

import com.wenchukai.blog.model.ArticleType;

public interface ArticleTypeMapper {
    int insert(ArticleType record);

    ArticleType selectByPrimaryKey(Integer id);

    List<ArticleType> selectAll();

    int updateByPrimaryKey(ArticleType record);
}