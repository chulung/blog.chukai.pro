package com.wchukai.web.quartz.job;

import com.wchukai.web.mapper.AppLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCronJob {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected AppLogMapper appLogMapper;
}
