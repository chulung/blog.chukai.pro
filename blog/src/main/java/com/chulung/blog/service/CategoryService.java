package com.chulung.blog.service;

import java.util.List;

import com.chulung.blog.model.Category;

public interface CategoryService {

	List<Category> getCategoryListByParentId(Integer parentId);
}
