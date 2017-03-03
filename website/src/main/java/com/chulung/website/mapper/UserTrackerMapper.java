package com.chulung.website.mapper;

import com.chulung.website.model.UserTracker;

import com.chulung.mybatis.mapper.BaseMapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;

public interface UserTrackerMapper extends BaseMapper<UserTracker>,SelectCountMapper<UserTracker>{

}
