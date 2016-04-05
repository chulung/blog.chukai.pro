package com.wenchukai.tracker.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.wenchukai.base.BaseModel;

public class VisitorInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3076762908203898932L;
	@Id
	private Integer id;

	private String ip;

	private String userAgent;

	private LocalDateTime accessTime;

	private String accessUrl;
	
	private String serverName;
	
	private String tuid;

	public VisitorInfo(String ip, String userAgent, LocalDateTime accessTime, String accessUrl,String serverName,String tuid) {
		super();
		this.ip = ip;
		this.userAgent = userAgent;
		this.accessTime = accessTime;
		this.accessUrl = accessUrl;
		this.setServerName(serverName);
		this.tuid=tuid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent == null ? null : userAgent.trim();
	}

	public LocalDateTime getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(LocalDateTime accessTime) {
		this.accessTime = accessTime;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl == null ? null : accessUrl.trim();
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}
}