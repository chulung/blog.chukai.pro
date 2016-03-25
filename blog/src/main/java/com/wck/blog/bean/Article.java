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
public class Article extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2211114897454091963L;
	@Id
	private Integer id;

	private String author;
	/** 最后修改人 */
	private String mender;

	private String context;

	private LocalDateTime createTime;

	private Integer isDelete;

	private String title;

	private Integer typeId;

	private LocalDateTime updateTime;

	private Integer userId;
	private Integer version;
	
	public static Article of(ArticleDraft articleDraft) {
		Article article = new Article();
		article.setId(articleDraft.getArticleId());
		article.setContext(articleDraft.getHtmlContext());
		article.setUpdateTime(articleDraft.getUpdateTime());
		article.setAuthor(articleDraft.getAuthor());
		article.setIsDelete(articleDraft.getIsDelete());
		article.setMender(articleDraft.getMender());
		article.setTitle(articleDraft.getTitle());
		article.setUserId(articleDraft.getUserId());
		article.setCreateTime(articleDraft.getCreateTime());
		article.setTypeId(articleDraft.getTypeId());
		article.setVersion(articleDraft.getVersion());
		return article;
	}

	public Article() {
	}

	public Article(Integer id) {
		this.id = id;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}
