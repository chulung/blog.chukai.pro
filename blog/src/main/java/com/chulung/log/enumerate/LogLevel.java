package com.chulung.log.enumerate;

import com.chulung.ckbatis.enums.ValuedEnum;

/**
 * 日志级别枚举
 * 
 * @author chulung1
 *
 */
public enum LogLevel implements ValuedEnum<LogLevel> {
	ERROR(0, "error"), INFO(1, "info");
	private int value;
	private String desc;

	private LogLevel(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}

}