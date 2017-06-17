package com.chulung.website.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chulung.website.mapper.AppLogMapper;

public abstract class AbstractCronJob {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected AppLogMapper appLogMapper;
}
