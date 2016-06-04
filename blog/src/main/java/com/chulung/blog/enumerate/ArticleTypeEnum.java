package com.chulung.blog.enumerate;

public enum ArticleTypeEnum {
	ORIGINAL(1, "原创"), ESSAIES(2, "随便"), REPOST(3, "转载"), OTHER(4, "其他");
	private Integer code;
	private String dedcription;

	private ArticleTypeEnum(Integer code, String dedcription) {
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
