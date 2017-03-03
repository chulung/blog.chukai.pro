package com.chulung.website.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Transient;

import com.chulung.website.model.Article;

public class   ArticleDto extends Article {

	private static final long serialVersionUID = -6414652104447372040L;

	@Transient
	private LocalDateTime createTimeStart;
	@Transient
	private LocalDateTime createTimeEnd;

	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

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
