package com.wenchukai.blog.model;

import java.util.Date;

import com.wenchukai.bean.BaseModel;

public class ArticleDraftHistory extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 442964929728441887L;

	private Integer id;

    private Integer articleId;

    private String title;

    private Date createTime;

    private Date updateTime;

    private String author;

    private Integer userId;

    private String mender;

    private Integer isPublish;

    private Integer typeId;

    private Integer isDelete;

    private Integer version;

    private String changLog;

    private String context;

    private String htmlContext;

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

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getHtmlContext() {
        return htmlContext;
    }

    public void setHtmlContext(String htmlContext) {
        this.htmlContext = htmlContext == null ? null : htmlContext.trim();
    }
}