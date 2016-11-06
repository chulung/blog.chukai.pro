package com.chulung.craft.model;

import java.time.LocalDateTime;

import com.chulung.craft.enumerate.IsDeleteEnum;

public class Chatter {
	private Integer id;
	private String context;
	private LocalDateTime createTime;
	private IsDeleteEnum isDelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public IsDeleteEnum getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(IsDeleteEnum isDelete) {
		this.isDelete = isDelete;
	}

}
