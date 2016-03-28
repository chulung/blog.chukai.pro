package com.wenchukai.tracker.bean;

import java.util.Date;

import javax.persistence.Id;

import com.wenchukai.blog.bean.BaseBean;

public class VisitorInfo extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6814484659822641984L;
	@Id
	private String id;
	private String ip;
	private String userAgent;
	private Date accessTime;
	private String accessUrl;

	public VisitorInfo() {
	}
	
	public VisitorInfo( String ip, String userAgent, Date accessTime, String accessUrl) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.accessTime = accessTime;
		this.accessUrl = accessUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

}
