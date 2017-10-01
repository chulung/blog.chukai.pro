package com.wchukai.web.service.impl;

import com.wchukai.web.dto.out.SideBarInfo;
import com.wchukai.web.dto.out.SiteFooteInfo;
import com.wchukai.web.service.ArticleService;
import com.wchukai.web.service.ArticleTagService;
import com.wchukai.web.service.CommentService;
import com.wchukai.web.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by wchukai on 2017/3/21.
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentsService;

    @Override
    @Cacheable(cacheNames = "halfhour", key = "'siteFooterInfo'")
    public SiteFooteInfo getSiteFooteInfo() {
        SiteFooteInfo siteFooteInfo = new SiteFooteInfo();
        siteFooteInfo.setTags(this.articleTagService.findAllArticleTag());
        siteFooteInfo.setRecommendedArticles(this.articleService.findRecommendedArticles());
        return siteFooteInfo;
    }

    @Override
    @Cacheable(cacheNames = "halfhour", key = "'sideBarInfo'")
    public SideBarInfo getSideBarInfo() {
        SideBarInfo sideBarInfo = new SideBarInfo();
        sideBarInfo.setArchives(this.articleService.getArchive());
        sideBarInfo.setRecentUpdateArticles(this.articleService.recentUpdateArticles());
        sideBarInfo.setRecentlyComments(this.commentsService.findRecentlyComments());
        return sideBarInfo;
    }
}
