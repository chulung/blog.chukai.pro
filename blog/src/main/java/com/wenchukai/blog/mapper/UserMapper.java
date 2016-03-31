package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.User;
import java.util.List;

public interface UserMapper {
    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User selectOneBySelective(User record);
}