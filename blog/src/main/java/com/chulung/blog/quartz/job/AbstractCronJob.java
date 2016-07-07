package com.chulung.blog.quartz.job;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chulung.blog.enumerate.LogLevel;
import com.chulung.blog.enumerate.LogType;
import com.chulung.blog.mapper.AppLogMapper;
import com.chulung.blog.model.AppLog;

public abstract class AbstractCronJob {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppLogMapper cronJobLogMapper;

	public void mainHandler() {
		try {
			Component component = this.getClass().getAnnotation(Component.class);
			cronJobLogMapper.insertSelective(new AppLog(LogType.CRON_JOB_LOG, LogLevel.INFO,
					component != null ? component.value() + " starting" : "", LocalDateTime.now()));
			execute();
			cronJobLogMapper.insertSelective(new AppLog(LogType.CRON_JOB_LOG, LogLevel.INFO,
					component != null ? component.value() + " end" : "", LocalDateTime.now()));
		} catch (Exception e) {
			logger.error("",e);
			cronJobLogMapper.insertSelective(
					new AppLog(LogType.CRON_JOB_LOG, LogLevel.ERROR, e.toString(), LocalDateTime.now()));
		}
	}

	public abstract void execute() throws Exception;
}
