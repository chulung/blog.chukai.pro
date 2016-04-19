package com.wenchukai.log.enumerate;

import com.wenchukai.ckbatis.enums.ValuedEnum;

public enum LogType implements ValuedEnum<LogType> {
	CRON_JOB_LOG(0, "定时器日志");

	private int value;
	private String desc;

	private LogType(int value, String desc) {
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
