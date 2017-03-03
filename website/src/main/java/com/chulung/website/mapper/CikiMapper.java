package com.chulung.website.mapper;

import java.util.List;

import com.chulung.website.model.Ciki;
import com.chulung.mybatis.mapper.BaseMapper;

public interface CikiMapper extends BaseMapper<Ciki> {

	List<Ciki> getCikiTitelListByParentId(Ciki record);

}
