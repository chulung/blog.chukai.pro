package com.wenchukai.blog.model;

import com.wenchukai.base.BaseModel;


public class Config extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1126303022719698976L;

	private Integer id;

    private String key;

    private String value;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}