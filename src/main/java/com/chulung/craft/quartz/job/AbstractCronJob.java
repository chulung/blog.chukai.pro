package com.chulung.craft.quartz.job;

import java.time.LocalDateTime;

import com.chulung.craft.enumerate.LogType;
import com.chulung.craft.model.AppLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chulung.craft.enumerate.LogLevel;
import com.chulung.craft.mapper.AppLogMapper;

public abstract class AbstractCronJob {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected AppLogMapper cronJobLogMapper;

	public void mainHandler() {
		try {
			String name = this.getClass().getName();
			cronJobLogMapper.insertSelective(
					new AppLog(LogType.CRON_JOB_LOG, LogLevel.INFO, name + " starting", LocalDateTime.now()));
			execute();
			cronJobLogMapper.insertSelective(
					new AppLog(LogType.CRON_JOB_LOG, LogLevel.INFO, name + " end", LocalDateTime.now()));
		} catch (Exception e) {
			logger.error("", e);
			cronJobLogMapper.insertSelective(
					new AppLog(LogType.CRON_JOB_LOG, LogLevel.ERROR, e.toString(), LocalDateTime.now()));
		}
	}

	public abstract void execute() throws Exception;
}
