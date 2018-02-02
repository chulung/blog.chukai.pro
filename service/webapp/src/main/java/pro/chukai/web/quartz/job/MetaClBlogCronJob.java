package pro.chukai.web.quartz.job;

import pro.chukai.web.service.MetaClBlogLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetaClBlogCronJob extends AbstractCronJob {

    @Autowired
    private MetaClBlogLogService metaClBlogLogService;

//    @Scheduled(cron = "0 0 9 * * ?")
    public void execute() throws Exception {
        this.metaClBlogLogService.pushBlog();

    }

}
