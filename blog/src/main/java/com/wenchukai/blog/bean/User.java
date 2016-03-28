package com.wenchukai.blog.bean;

import javax.persistence.Id;

public class User extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7715819477347596407L;

	@Id
	private Integer id;

	private String userName;

	private String password;
	
	private String nickName;
	
	private Integer authority;

	private String sessionId;

	private Integer rememberLogin;
	
	public User() {
	}
	
	
	public User(String sessionId, Integer rememberLogin) {
		this.sessionId = sessionId;
		this.rememberLogin = rememberLogin;
	}


	public User(String userName, String password, Integer authority) {
		this.userName = userName;
		this.password = password;
		this.authority = authority;
	}


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
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Integer getRememberLogin() {
		return rememberLogin;
	}

	public void setRememberLogin(Integer rememberLogin) {
		this.rememberLogin = rememberLogin;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
