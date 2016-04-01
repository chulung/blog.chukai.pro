package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.Config;

public interface ConfigMapper {
    int insertSelective(Config record);
    Config selectByPrimaryKey(Integer id);
}