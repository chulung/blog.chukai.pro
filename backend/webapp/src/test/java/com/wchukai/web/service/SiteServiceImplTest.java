package com.wchukai.web.service;

import com.wchukai.test.SpringbootBaseTest;
import com.wchukai.web.dto.out.SideBarInfo;
import com.wchukai.web.dto.out.SiteFooteInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wchukai on 2017/3/23.
 */
public class SiteServiceImplTest extends SpringbootBaseTest {
    @Autowired
    private SiteService siteService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Test
    public void getSiteFooteInfo() throws Exception {
        SiteFooteInfo siteFooteInfo = siteService.getSiteFooteInfo();
        assertThat(siteFooteInfo.getTags()).isNotEmpty();
        assertThat(siteFooteInfo.getTags().get(0)).isEqualToComparingFieldByField(this.articleTagService.findAllArticleTag().get(0));
        assertThat(siteFooteInfo.getRecommendedArticles().get(0)).isEqualToComparingFieldByField(this.articleService.findRecommendedArticles().get(0));
    }

    @Test
    public void getSideBarInfo() throws Exception {
        SideBarInfo sideBarInfo = siteService.getSideBarInfo();
        assertThat(sideBarInfo.getArchives().get(0)).isEqualToComparingFieldByField(this.articleService.getArchive().get(0));
        assertThat(sideBarInfo.getRecentUpdateArticles().get(0)).isEqualToComparingFieldByField(this.articleService.recentUpdateArticles().get(0));
        assertThat(sideBarInfo.getRecentlyComments().get(0)).isEqualToComparingFieldByField(this.commentService.findRecentlyComments().get(0));
    }
}