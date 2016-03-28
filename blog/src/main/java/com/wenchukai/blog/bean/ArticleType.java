package com.wenchukai.blog.bean;

import javax.persistence.Id;

public class ArticleType {
	@Id
	private Integer id;

	private String typeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
