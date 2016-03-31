package com.wenchukai.blog.service;

import java.util.List;
import java.util.Optional;

import com.github.pagehelper.PageInfo;
import com.wenchukai.blog.dto.Blog;
import com.wenchukai.blog.dto.CommonInfo;

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
