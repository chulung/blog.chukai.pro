package com.wenchukai.blog.dto;

public class PageIn {
	private int page;
	private int pageSize;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize <= 0 ? 1 : page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 20 : pageSize;
	}
}
