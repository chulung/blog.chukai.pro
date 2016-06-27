package com.chulung.blog.dto;

import java.time.LocalDateTime;

import javax.persistence.Transient;

import com.chulung.blog.model.Article;

public class ArticleDto extends Article {

	private static final long serialVersionUID = -6414652104447372040L;

	@Transient
	private LocalDateTime createTimeStart;
	@Transient
	private LocalDateTime createTimeEnd;

	public LocalDateTime getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(LocalDateTime createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public LocalDateTime getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

}
