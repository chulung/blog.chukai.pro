package com.chulung.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.chulung.blog.enumerate.DictionaryTypeEnum;

public class Dictionary extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2144165591405801290L;
	@Id
	private Integer id;
	private DictionaryTypeEnum dictType;
	private String dictValue;
	private String dictDesc;
	public String getDictDesc() {
		return dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	private LocalDateTime createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DictionaryTypeEnum getDictType() {
		return dictType;
	}

	public void setDictType(DictionaryTypeEnum dictType) {
		this.dictType = dictType;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}