package com.chulung.craft.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.chulung.craft.enumerate.CateLevelEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ciki {
	@Id
	private Integer id;
	private Integer parentId;
	private String title;
	private String enIndex;
	private CateLevelEnum cateLevel;
	private String markdown;
	private String html;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

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

	public CateLevelEnum getCateLevel() {
		return cateLevel;
	}

	public void setCateLevel(CateLevelEnum cateLevel) {
		this.cateLevel = cateLevel;
	}

	public String getEnIndex() {
		return enIndex;
	}

	public void setEnIndex(String enIndex) {
		this.enIndex = enIndex;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}