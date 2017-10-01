package com.wchukai.web.model;

import com.wchukai.common.databind.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "comments")
public class Comment extends BaseModel {


    private static final long serialVersionUID = -8033788015527222581L;
    private Integer articleId;

    private Integer replyId;

    private String comment;

    private String website;

    private String uri;

    //按时间倒序
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @OrderBy("desc")
    private LocalDateTime createTime;

    private String userName;

    private String email;


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Comment() {
    }

    public Comment(Integer articleId) {
        this.articleId = articleId;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}