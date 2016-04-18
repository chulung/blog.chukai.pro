package com.wenchukai.blog.model;

import java.util.List;

import com.wenchukai.blog.dto.PageIn;

public class PaginationResult<T> {
	private List<T> list;
	private PageIn pageIn;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageIn getPageIn() {
		return pageIn;
	}

	public void setPageIn(PageIn pageIn) {
		this.pageIn = pageIn;
	}
}
