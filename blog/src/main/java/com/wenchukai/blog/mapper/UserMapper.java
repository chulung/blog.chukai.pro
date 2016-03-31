package com.wenchukai.blog.mapper;

import java.util.List;

import com.wenchukai.blog.model.User;

public interface UserMapper {
    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User selectOneBySelective(User record);
}