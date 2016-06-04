package com.chulung.blog.enumerate;

public enum PublishStatusEnum {
	PUBLISHED(1, "发布"), UNPUBLISHED(0, "未发布");
	private Integer code;
	private String dedcription;

	private PublishStatusEnum(Integer code, String dedcription) {
		this.code = code;
		this.dedcription = dedcription;
	}

	public Integer getCode() {
		return code;
	}

	public String getDedcription() {
		return dedcription;
	}

}
