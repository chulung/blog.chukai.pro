package com.wenchukai.blog.model;


import java.time.LocalDateTime;

import com.wenchukai.base.BaseModel;


public class ArticleType extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2144165591405801290L;

	private Integer id;

    private String typeName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}