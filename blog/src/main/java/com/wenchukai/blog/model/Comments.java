package com.wenchukai.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.wenchukai.base.BaseModel;

public class Comments extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8033788015527222581L;
	@Id
	private Integer id;

	private Integer articleId;

	private Integer replyId;

	private String comment;

	private LocalDateTime createTime;

	private String userName;

	private String email;

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

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}
}