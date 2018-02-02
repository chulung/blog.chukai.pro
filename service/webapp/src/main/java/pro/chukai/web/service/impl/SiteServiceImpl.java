package pro.chukai.web.service.impl;

import pro.chukai.web.dto.out.SideBarInfo;
import pro.chukai.web.dto.out.SiteFooteInfo;
import pro.chukai.web.service.ArticleService;
import pro.chukai.web.service.ArticleTagService;
import pro.chukai.web.service.CommentService;
import pro.chukai.web.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pro.chukai.web.dto.out.SideBarInfo;
import pro.chukai.web.service.CommentService;
import pro.chukai.web.service.SiteService;

/**
 * Created by chukai on 2017/3/21.
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
