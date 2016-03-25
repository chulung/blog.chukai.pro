package com.wck.blog.service;

import java.util.List;
import java.util.Optional;

import com.wck.blog.dto.Blog;
import com.wck.blog.dto.CommonInfo;

public interface BlogService {
	/**
	 * 查询最新的博客,用于首页显示
	 * 
	 * @param page
	 * @param articleType 
	 * @return
	 */
	List<Blog> findNewBlog(Optional<Integer> page, Integer typeId);

	CommonInfo getCommonInfo();

	List<Blog> getBlogsByYearMonth(Integer year, Integer month);

	int getBlogPageCount(Integer typeId);
}

