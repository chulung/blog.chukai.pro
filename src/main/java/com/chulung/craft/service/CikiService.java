package com.chulung.craft.service;

import java.util.List;

import com.chulung.craft.dto.CikiTreeNode;
import com.chulung.craft.dto.JsonResult;
import com.chulung.craft.model.Ciki;

public interface CikiService {

	List<CikiTreeNode> getCategoryTreeNode();

	JsonResult<Ciki> getCikiById(Integer id);

	void addCiki(Ciki ciki);

	void updateCiki(Ciki ciki);
}
