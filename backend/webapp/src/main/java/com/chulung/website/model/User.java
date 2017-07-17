package com.chulung.website.model;

public class User extends BaseModel {

	private static final long serialVersionUID = -6451695282340873011L;

	private String userName;

	private String password;

	private String nickName;

	private String sessionId;

	private Integer remember;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId == null ? null : sessionId.trim();
	}

	public Integer getRemember() {
		return remember;
	}

	public void setRemember(Integer remember) {
		this.remember = remember;
	}
}