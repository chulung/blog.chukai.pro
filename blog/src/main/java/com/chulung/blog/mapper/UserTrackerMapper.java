package com.chulung.blog.mapper;

import com.chulung.blog.model.UserTracker;

import tk.mybatis.mapper.common.base.select.SelectCountMapper;

public interface UserTrackerMapper extends BaseMapper<UserTracker> ,SelectCountMapper<UserTracker>{

}
