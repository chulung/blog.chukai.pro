package com.chulung.website.model;

import java.time.LocalDateTime;

import javax.persistence.Transient;

import com.chulung.common.databind.LocalDateTimeDeserializer;
import com.chulung.website.enumerate.IsDeleteEnum;
import com.chulung.website.enumerate.PublishStatusEnum;
import com.chulung.common.databind.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDraft extends BaseModel {

    private static final long serialVersionUID = -8858052175630827783L;

    private Integer articleId;

    private String title;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    private String author;

    private PublishStatusEnum isPublish;

    private Integer columnId;

    private IsDeleteEnum isDelete;

    private Integer version;

    private String content;

    private String htmlContent;

    @Transient
    private String license;

    private String tags;

    /**
     * 是否推送博客到其他网站 0 否 1是
     */
    @Transient
    private int pushBlog;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

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

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
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