package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.ArticleType;
import java.util.List;

public interface ArticleTypeMapper {
    int insert(ArticleType record);

    ArticleType selectByPrimaryKey(Integer id);

    List<ArticleType> selectAll();

    int updateByPrimaryKey(ArticleType record);
}