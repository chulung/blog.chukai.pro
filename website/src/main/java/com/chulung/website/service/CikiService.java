package com.chulung.website.service;

import java.util.List;

import com.chulung.website.dto.CikiTreeNode;
import com.chulung.website.dto.JsonResult;
import com.chulung.website.model.Ciki;

public interface CikiService {

	List<CikiTreeNode> getCategoryTreeNode();

	JsonResult<Ciki> getCikiById(Integer id);

	void addCiki(Ciki ciki);

	void updateCiki(Ciki ciki);
}
