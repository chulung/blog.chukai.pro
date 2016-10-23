package com.chulung.blog.quartz.job;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chulung.blog.enumerate.LogLevel;
import com.chulung.blog.enumerate.LogType;
import com.chulung.blog.mapper.AppLogMapper;
import com.chulung.blog.model.AppLog;

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
