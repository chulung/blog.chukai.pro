package com.chulung.blog.service;

import java.util.List;

import com.chulung.blog.model.Ciki;

public interface CikiService {

	List<Ciki> getCikiTitelListByParentId(Integer parentId);
}
