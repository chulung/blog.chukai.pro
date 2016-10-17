package com.chulung.blog.dto;

public class PageIn<T> {
	private int page;
	private int pageSize;
	private T record;

	public PageIn() {
	}

	public PageIn(int page, int pageSize, T record) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.record=record;
	}

	public int getPage() {
		return page <= 0 ? 1 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize <= 0 ? 20 : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 20 : pageSize;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

}
