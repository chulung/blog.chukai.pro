package com.chulung.blog.mapper;

import java.util.List;

import com.chulung.blog.model.Ciki;

public interface CikiMapper extends BaseMapper<Ciki>{

	List<Ciki> getCikiTitelListByParentId(Ciki record);

}
