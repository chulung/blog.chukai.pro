package com.chulung.website.enumerate;

public enum IsDeleteEnum {
	Y(0, "已删除"), N(1, "未删除");
	private Integer code;
	private String dedcription;

	private IsDeleteEnum(Integer code, String dedcription) {
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
