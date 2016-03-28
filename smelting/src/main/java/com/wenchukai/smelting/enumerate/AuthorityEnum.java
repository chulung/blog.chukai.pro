package com.wenchukai.smelting.enumerate;

public enum AuthorityEnum {
	/**超级管理员*/
	SUPERADMIN(-1, "超级管理员"),
	/**游客*/
	VISITOR(0,"游客"),
	/**普通用户*/
	COMMON_USER(1,"普通用户");
	private Integer code;
	private String dedcription;
	private AuthorityEnum(Integer code, String dedcription) {
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
