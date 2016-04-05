package com.wenchukai.tracker.mapper;

import com.wenchukai.ckbatis.mapper.BaseMapper;
import com.wenchukai.tracker.model.UserTracker;

import tk.mybatis.mapper.common.base.select.SelectCountMapper;

public interface UserTrackerMapper extends BaseMapper<UserTracker> ,SelectCountMapper<UserTracker>{

}
