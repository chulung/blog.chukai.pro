package com.chulung.website.service;

import com.chulung.website.dto.out.SideBarInfo;
import com.chulung.website.dto.out.SiteFooteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by chulung on 2017/3/21.
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentsService commentsService;

    @Override
    @Cacheable(cacheNames = "halfhour")
    public SiteFooteInfo getSiteFooteInfo() {
        SiteFooteInfo siteFooteInfo = new SiteFooteInfo();
        siteFooteInfo.setTags(this.articleTagService.findAllArticleTag());
        siteFooteInfo.setRecommendedArticles(this.articleService.findRecommendedArticles());
        return siteFooteInfo;
    }

    @Override
    @Cacheable(cacheNames = "halfhour")
    public SideBarInfo getSideBarInfo() {
        SideBarInfo sideBarInfo = new SideBarInfo();
        sideBarInfo.setArticleFilings(this.articleService.getArticleFilings());
        sideBarInfo.setPopularArticles(this.articleService.findPopularArticles());
        sideBarInfo.setRecentlyComments(this.commentsService.findRecentlyComments());
        return sideBarInfo;
    }
}
