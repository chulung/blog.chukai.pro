package com.chulung.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.chulung.blog.enumerate.LogLevel;
import com.chulung.blog.enumerate.LogType;

public class AppLog extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1775282535859780116L;
	@Id
	private Integer id;
	private LogType type;
	private LogLevel level;
	private String log;
	private LocalDateTime createTime;

	public AppLog() {
	}

	public AppLog(LogType type, LogLevel level, String log) {
		this(type, level, log, LocalDateTime.now());
	}
	public AppLog(LogType type, LogLevel level, String log, LocalDateTime createTime) {
		super();
		this.type = type;
		this.level = level;
		this.log = log;
		this.setCreateTime(createTime);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LogType getType() {
		return type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
