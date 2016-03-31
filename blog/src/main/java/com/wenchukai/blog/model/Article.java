package com.wenchukai.blog.model;

import java.util.Date;

public class Article extends BaseModel {
	private Integer id;

	private String title;

	private Date createTime;

	private Date updateTime;

	private String author;

	private Integer userId;

	private String mender;

	private Integer typeId;

	private String derivationUrl;

	private Integer version;

	private Integer isdelete;

	private String changLog;

	private String context;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMender() {
		return mender;
	}

	public void setMender(String mender) {
		this.mender = mender == null ? null : mender.trim();
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDerivationUrl() {
		return derivationUrl;
	}

	public void setDerivationUrl(String derivationUrl) {
		this.derivationUrl = derivationUrl == null ? null : derivationUrl.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public String getChangLog() {
		return changLog;
	}

	public void setChangLog(String changLog) {
		this.changLog = changLog == null ? null : changLog.trim();
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context == null ? null : context.trim();
	}

	public static Article of(ArticleDraft articleDraft) {
		Article article = new Article();
		article.setId(articleDraft.getArticleId());
		article.setContext(articleDraft.getHtmlContext());
		article.setUpdateTime(articleDraft.getUpdateTime());
		article.setAuthor(articleDraft.getAuthor());
		article.setIsdelete(articleDraft.getIsDelete());
		article.setMender(articleDraft.getMender());
		article.setTitle(articleDraft.getTitle());
		article.setUserId(articleDraft.getUserId());
		article.setCreateTime(articleDraft.getCreateTime());
		article.setTypeId(articleDraft.getTypeId());
		article.setVersion(articleDraft.getVersion());
		return article;
	}
}