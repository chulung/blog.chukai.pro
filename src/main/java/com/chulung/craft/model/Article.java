package com.chulung.craft.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.chulung.craft.enumerate.IsDeleteEnum;
import com.chulung.jackson.databind.LocalDateTimeSerializer;
import com.chulung.search.DocType;
import com.chulung.search.Indexable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.StringEscapeUtils;


public class Article extends BaseModel implements Indexable{
    /**
     *
     */
    private static final long serialVersionUID = -1692088776366835421L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    private String author;

    private Integer typeId;

    private String derivationUrl;

    private Integer version;

    private IsDeleteEnum isDelete;

    private String context;
    private Integer commentCount;
    private Integer visitCount;
    private String pic;
    @Transient
    private String typeName;

    private String license;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

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

    @Override
    public DocType docType() {
        return DocType.ART;
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