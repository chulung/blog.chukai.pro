package pro.chukai.web.quartz.job;

import pro.chukai.web.mapper.AppLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pro.chukai.web.mapper.AppLogMapper;

public abstract class AbstractCronJob {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected AppLogMapper appLogMapper;
}
