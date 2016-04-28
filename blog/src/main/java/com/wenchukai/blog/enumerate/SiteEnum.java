package com.wenchukai.blog.enumerate;

public enum SiteEnum {
	CNBLOGS(0, "博客园");
	private Integer code;
	private String dedcription;

	private SiteEnum(Integer code, String dedcription) {
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
