package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.VisitorInfo;
import java.util.List;

public interface VisitorInfoMapper {
    int insert(VisitorInfo record);

    VisitorInfo selectByPrimaryKey(Integer id);

    List<VisitorInfo> selectAll();

    int updateByPrimaryKey(VisitorInfo record);
}