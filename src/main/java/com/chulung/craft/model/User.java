package com.chulung.craft.model;

import javax.persistence.Id;

import com.chulung.craft.enumerate.AuthorityEnum;

public class User extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6451695282340873011L;
	@Id
	private Integer id;

	private String userName;

	private String password;

	private String nickName;

	private String sessionId;

	private AuthorityEnum authority;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public AuthorityEnum getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityEnum authority) {
		this.authority = authority;
	}

}