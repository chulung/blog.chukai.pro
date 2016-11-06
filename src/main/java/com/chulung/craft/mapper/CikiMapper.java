package com.chulung.craft.mapper;

import java.util.List;

import com.chulung.craft.model.Ciki;

public interface CikiMapper extends BaseMapper<Ciki>{

	List<Ciki> getCikiTitelListByParentId(Ciki record);

}
