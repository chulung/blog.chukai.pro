package pro.chukai.web.service.impl;

import pro.chukai.common.util.DateUtils;
import pro.chukai.metaweblog.MetaWeblog;
import pro.chukai.metaweblog.struct.Post;
import pro.chukai.web.enumerate.*;
import pro.chukai.web.mapper.AppLogMapper;
import pro.chukai.web.mapper.ArticleMapper;
import pro.chukai.web.mapper.MetaClBlogLogMapper;
import pro.chukai.web.model.AppLog;
import pro.chukai.web.model.Article;
import pro.chukai.web.model.MetaClBlogLog;
import pro.chukai.web.service.ConfigService;
import pro.chukai.web.service.MetaClBlogLogService;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.chukai.web.mapper.AppLogMapper;
import pro.chukai.web.model.AppLog;
import pro.chukai.web.model.Article;
import pro.chukai.web.model.MetaClBlogLog;
import pro.chukai.web.service.MetaClBlogLogService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 博客同步至其他网站
 * Created by chukai on 2016/11/8.
 */
@Service
public class MetaWeblogServiceImpl extends BaseService implements MetaClBlogLogService {
    public static final String PUSH_BLOG_DEFAULT = "0";
    public static final String AUTHOR_INFO = "<p>作者：<a href=\"https://chukai.link\">初开</a></p>\n" +
            "<p>发表于：<a href=\"http://www.cnblogs.com/wchukai\">博客园</a></p>";

    @Autowired
    protected AppLogMapper cronJobLogMapper;

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private MetaClBlogLogMapper metaWeBlogLogMapper;

    @Autowired
    private List<MetaWeblog> metaWeblogs;
    @Autowired
    private ConfigService configService;

    @Override
    public void pushBlog() throws XmlRpcException {
        String value = configService.getValueBykey(ConfigKeyEnum.PUSH_BLOG, PUSH_BLOG_DEFAULT);
        if (PUSH_BLOG_DEFAULT.equals(value)) {
            return;
        }
        for (MetaWeblog metaWeblog : metaWeblogs) {
            new PushTask(metaWeblog).start();
        }
    }

    @Override
    public void pushBlog(Integer id) throws XmlRpcException {
        Article article=articleMapper.selectByPrimaryKey(id);
        for (MetaWeblog metaWeblog : metaWeblogs) {
            pushArticle(article,metaWeblog);
        }
    }


    /**
     * 推送博客文章至其他网站
     *
     * @param article
     * @param metaWeblog
     * @return
     * @throws XmlRpcException
     */
    public boolean pushArticle(Article article, MetaWeblog metaWeblog) throws XmlRpcException {
        SiteEnum site = SiteEnum.valueOf(metaWeblog.getConfigInfo().getSiteName());
        MetaClBlogLog metaCLBlogLog = this.metaWeBlogLogMapper.selectOne(new MetaClBlogLog(article.getId(), site));
        Post post = new Post();
        post.setTitle(article.getTitle());
        post.setDateCreated(DateUtils.toDate(article.getCreateTime()));
        String description = article.getContent() + AUTHOR_INFO + configService.getValueBykey(ConfigKeyEnum.ARTICLE_LICENSE, "");
        post.setDescription(description);
        post.setMt_keywords(article.getTags());
        if (metaCLBlogLog != null) {
            if (article.getIsDelete() == IsDeleteEnum.Y) {
                metaWeblog.deletePost(metaCLBlogLog.getPostId());
            } else {
                post.setPostid(metaCLBlogLog.getPostId());
                // 发送编辑请求
                metaWeblog.editPost(post, true);
            }
            MetaClBlogLog record = new MetaClBlogLog();
            record.setId(metaCLBlogLog.getId());
            record.setLastestPostTime(LocalDateTime.now());
            metaWeBlogLogMapper.updateByPrimaryKeySelective(record);
            cronJobLogMapper.insertSelective(new AppLog(LogType.META_CK_BLOG_LOG, LogLevel.INFO,
                    String.format("博客《%s》更新推送成功", post.getTitle())));
        } else if (article.getIsDelete() == IsDeleteEnum.N) {
            // 发送新建博客请求
            String postId = metaWeblog.newPost(article.getId().toString(), post, true);
            MetaClBlogLog record = new MetaClBlogLog(postId, article.getId(), LocalDateTime.now(), site);
            metaWeBlogLogMapper.insertSelective(record);
            cronJobLogMapper.insertSelective(new AppLog(LogType.META_CK_BLOG_LOG, LogLevel.INFO,
                    String.format("博客《%s》新建推送成功", post.getTitle())));
        }
        return true;

    }

    private class PushTask extends Thread {
        private MetaWeblog metaWeblog;

        public PushTask(MetaWeblog metaWeblog) {
            this.metaWeblog = metaWeblog;
        }

        @Override
        public void run() {
            for (Article article : articleMapper.selectListForMetaClblog(metaWeblog.getConfigInfo().getSiteName())) {
                try {
                    pushArticle(article, metaWeblog);
                    Thread.sleep(60000);//每60秒推一次
                } catch (Exception e) {
                    logger.error("", e);
                    cronJobLogMapper.insertSelective(
                            new AppLog(LogType.CRON_JOB_LOG, LogLevel.ERROR, e.toString(), LocalDateTime.now()));
                    return;
                }
            }
        }
    }

}
