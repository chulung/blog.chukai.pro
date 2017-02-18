package com.chulung.craft.mapper;

import tk.mybatis.mapper.common.base.delete.DeleteMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.condition.DeleteByConditionMapper;

public interface BaseMapper<T>
		extends InsertSelectiveMapper<T>, UpdateByPrimaryKeySelectiveMapper<T>, SelectOneMapper<T>, SelectMapper<T>,SelectAllMapper<T>,SelectByPrimaryKeyMapper<T> ,DeleteMapper<T>{

}
