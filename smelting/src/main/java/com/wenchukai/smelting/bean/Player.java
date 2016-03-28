package com.wenchukai.smelting.bean;

import javax.persistence.Id;

public class Player {
	@Id
	private Integer id;
	private String userName;
	private String password;
	private Integer point;
	private Integer teamId;
	private String teamName;
	private String position;
	private Integer authority;
	private String sessionId;

	public Player() {
	}

	public Player(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Player(Integer id) {
		this.id=id;
	}

	public void setAuthority(Integer authority) {
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

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getAuthority() {
		return this.authority;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
