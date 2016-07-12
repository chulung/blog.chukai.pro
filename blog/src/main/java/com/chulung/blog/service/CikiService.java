package com.chulung.blog.service;

import java.util.List;

import com.chulung.blog.dto.JsonResult;
import com.chulung.blog.dto.CikiTreeNode;
import com.chulung.blog.model.Ciki;

public interface CikiService {

	List<CikiTreeNode> getCategoryTreeNode();

	JsonResult<Ciki> getCikiById(Integer id);

	void addCiki(Ciki ciki);

	void updateCiki(Ciki ciki);

}
