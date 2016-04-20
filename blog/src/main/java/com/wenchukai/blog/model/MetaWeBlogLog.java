package com.wenchukai.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

public class MetaWeBlogLog {
	@Id
	private Integer id;

	private String postId;
	private Integer articleId;
	private LocalDateTime lastestPostTime;
	private String site;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
