package com.wck.blog.bean;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wck.jackson.databind.LocalDateTimeSerializer;

/**
 * 
 * @author chukai
 *
 */
public class ArticleDraft extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2211114897454091963L;

	@Id
	private Integer id;
	private Integer articleId;
	private String author;
	private String mender;
	private String context;
	private String htmlContext;
	private LocalDateTime createTime;
	private Integer isDelete;
	private String title;
	private Integer typeId;
	private LocalDateTime updateTime;
	private Integer version;
	private Integer isPublish;
	private Integer userId;
	public ArticleDraft() {
	}

	public ArticleDraft(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public String getMender() {
		return mender;
	}

	public void setMender(String mender) {
		this.mender = mender;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getHtmlContext() {
		return htmlContext;
	}

	public void setHtmlContext(String htmlContext) {
		this.htmlContext = htmlContext;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
