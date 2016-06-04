package com.chulung.blog.model;

import java.util.List;

import com.github.pagehelper.Page;
import com.chulung.blog.dto.PageIn;

public class PaginationResult<T> {
	private List<T> list;
	private PageIn<T> pageIn;

	public PaginationResult(List<T> list, PageIn<T> pageIn) {
		super();
		this.list = list;
		this.pageIn = pageIn;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageIn<T> getPageIn() {
		return pageIn;
	}

	public void setPageIn(PageIn<T> pageIn) {
		this.pageIn = pageIn;
	}

	public static <T> PaginationResult<T> of(Page<T> page) {
		return new PaginationResult<>(page, new PageIn<T>(page.getPageNum(), page.getPageSize(), null));

	}

}
