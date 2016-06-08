package com.chulung.blog.service;

import java.util.List;
import java.util.Optional;

import com.chulung.blog.dto.Blog;
import com.chulung.blog.dto.CommonInfo;
import com.github.pagehelper.PageInfo;

public interface BlogService {
	/**
	 * 查询最新的博客,用于首页显示
	 * 
	 * @param page
	 * @param articleType
	 * @return
	 */
	PageInfo<Blog> selectBySelectiveForBlog(Optional<Integer> page, Integer typeId);

	CommonInfo getCommonInfo();

	List<Blog> getBlogsByYearMonth(Integer year, Integer month);
}
