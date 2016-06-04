package com.chulung.tracker.mapper;

import com.chulung.ckbatis.mapper.BaseMapper;
import com.chulung.tracker.model.UserTracker;

import tk.mybatis.mapper.common.base.select.SelectCountMapper;

public interface UserTrackerMapper extends BaseMapper<UserTracker> ,SelectCountMapper<UserTracker>{

}
