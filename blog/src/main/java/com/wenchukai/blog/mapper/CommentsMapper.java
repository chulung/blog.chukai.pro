package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.Comments;

public interface CommentsMapper {

    int insertSelective(Comments record);

    Comments selectBySelective(Comments record);

}