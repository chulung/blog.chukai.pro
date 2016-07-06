package com.chulung.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import org.apache.commons.lang3.StringEscapeUtils;

import com.chulung.blog.enumerate.IsDeleteEnum;

public class Article extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1692088776366835421L;

	@Id
	private Integer id;

	private String title;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	private String author;

	private Integer typeId;

	private String derivationUrl;

	private Integer version;

	private IsDeleteEnum isDelete;

	private String context;
	private Integer commentCount;
	private Integer visitCount;

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
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

	public IsDeleteEnum getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(IsDeleteEnum isDelete) {
		this.isDelete = isDelete;
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
		article.setContext(StringEscapeUtils.unescapeHtml4(articleDraft.getHtmlContext()));
		article.setUpdateTime(articleDraft.getUpdateTime());
		article.setAuthor(articleDraft.getAuthor());
		article.setIsDelete(articleDraft.getIsDelete());
		article.setTitle(articleDraft.getTitle());
		article.setCreateTime(articleDraft.getCreateTime());
		article.setTypeId(articleDraft.getTypeId());
		article.setVersion(articleDraft.getVersion());
		return article;
	}
}