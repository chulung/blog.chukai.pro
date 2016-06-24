package com.chulung.blog.model;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;

public class Ciki {
	@Id
	private Integer id;
	private Integer parentId;
	private String title;
	private Integer type;
	private String markdown;
	@Transient
	private List<Ciki> cikis;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMarkdown() {
		return markdown;
	}
	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}
	public List<Ciki> getCikis() {
		return cikis;
	}
	public void setCikis(List<Ciki> cikis) {
		this.cikis = cikis;
	}
	
}