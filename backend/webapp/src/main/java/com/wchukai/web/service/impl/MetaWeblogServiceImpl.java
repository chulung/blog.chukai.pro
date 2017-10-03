package com.wchukai.web.service.impl;

import com.wchukai.common.util.DateUtils;
import com.wchukai.metaweblog.MetaWeblog;
import com.wchukai.metaweblog.struct.Post;
import com.wchukai.web.enumerate.*;
import com.wchukai.web.mapper.AppLogMapper;
import com.wchukai.web.mapper.ArticleMapper;
import com.wchukai.web.mapper.MetaClBlogLogMapper;
import com.wchukai.web.model.AppLog;
import com.wchukai.web.model.Article;
import com.wchukai.web.model.MetaClBlogLog;
import com.wchukai.web.service.ConfigService;
import com.wchukai.web.service.MetaClBlogLogService;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 博客同步至其他网站
 * Created by wchukai on 2016/11/8.
 */
@Service
public class MetaWeblogServiceImpl extends BaseService implements MetaClBlogLogService {
    public static final String METACKBLOG_COMMENTS = "<p>作者：初开</p><p>原文链接:<a href=\"https://wchukai.com/article/%s\">https://wchukai.com/article/%s</a></p><p>本文由<a href=\"https://github.com/wchukai/MetaCLblog\">MetaCLBlog</a>于%s自动同步至%s</p>";

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
        for (MetaWeblog metaWeblog : metaWeblogs) {
            new PushTask(metaWeblog).start();
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
        String description = article.getContent()
                + String.format(METACKBLOG_COMMENTS, article.getUri(), article.getUri(),
                DateUtils.format(LocalDateTime.now()), site.getDedcription()) + configService.getValueBykey(ConfigKeyEnum.ARTICLE_LICENSE, "");
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
