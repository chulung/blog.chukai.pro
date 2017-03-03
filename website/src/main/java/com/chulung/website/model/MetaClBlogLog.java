package com.chulung.website.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.chulung.website.enumerate.SiteEnum;

public class MetaClBlogLog extends BaseModel{

	private String postId;
	private Integer articleId;
	private LocalDateTime lastestPostTime;
	private SiteEnum site;

	public MetaClBlogLog() {
	}

	public MetaClBlogLog(String postId, Integer articleId, LocalDateTime lastestPostTime, SiteEnum site) {
		super();
		this.postId = postId;
		this.articleId = articleId;
		this.lastestPostTime = lastestPostTime;
		this.site = site;
	}

	public MetaClBlogLog(Integer articleId, SiteEnum site) {
		this.articleId = articleId;
		this.site=site;
	}


	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public LocalDateTime getLastestPostTime() {
		return lastestPostTime;
	}

	public void setLastestPostTime(LocalDateTime lastestPostTime) {
		this.lastestPostTime = lastestPostTime;
	}

	public SiteEnum getSite() {
		return site;
	}

	public void setSite(SiteEnum site) {
		this.site = site;
	}

}
