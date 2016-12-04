package com.chulung.craft.model;

import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.chulung.craft.enumerate.IsDeleteEnum;
import com.chulung.craft.enumerate.PublishStatusEnum;
import com.chulung.jackson.databind.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ArticleDraft extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8858052175630827783L;
	@Id
	private Integer id;

	private Integer articleId;

	private String title;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	private String author;

	private PublishStatusEnum isPublish;

	private Integer typeId;

	private IsDeleteEnum isDelete;

	private Integer version;

	private String content;

	private String htmlContent;

	@Transient
	private String license;

	/**
	 * 是否推送博客到其他网站 0 否 1是
	 */
	@Transient
	private int pushBlog;

	public int getPushBlog() {
		return pushBlog;
	}

	public void setPushBlog(int pushBlog) {
		this.pushBlog = pushBlog;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public PublishStatusEnum getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(PublishStatusEnum isPublish) {
		this.isPublish = isPublish;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public IsDeleteEnum getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(IsDeleteEnum isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent == null ? null : htmlContent.trim();
	}
}