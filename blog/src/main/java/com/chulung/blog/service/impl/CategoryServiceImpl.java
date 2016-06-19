package com.chulung.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.blog.mapper.CategoryMapper;
import com.chulung.blog.model.Category;
import com.chulung.blog.service.ArticleService;
import com.chulung.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ArticleService artilceService;

	@Override
	public List<Category> getCategoryListByParentId(Integer parentId) {
		Category record = new Category();
		record.setParentId(parentId);
		return categoryMapper.select(record).parallelStream().map(c -> {
			if (c.getArticleId() != null) {
				c.setArticleDraft(this.artilceService.findArticleDraft(c.getArticleId()));
			}
			return c;
		}).collect(Collectors.toList());
	}

}
