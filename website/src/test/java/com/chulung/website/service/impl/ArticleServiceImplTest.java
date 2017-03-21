package com.chulung.website.service.impl;

import static org.assertj.core.api.Assertions.*;
import com.chulung.BaseTest;
import com.chulung.website.dto.in.PageIn;
import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.enumerate.ConfigKeyEnum;
import com.chulung.website.enumerate.IsDeleteEnum;
import com.chulung.website.enumerate.PublishStatusEnum;
import com.chulung.website.mapper.ArticleMapper;
import com.chulung.website.model.ArticleDraft;
import com.chulung.website.model.User;
import com.chulung.website.service.ArticleService;
import com.chulung.website.service.ConfigService;
import com.chulung.website.session.WebSessionSupport;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

/**
 * Created by chulung on 2017/3/21.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleServiceImplTest extends BaseTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper mapper;
    private ArticleDraft publish;
    private ArticleDraft notPublish;

    @Autowired
    private ConfigService configService;

    @Autowired
    private WebSessionSupport webSessionSupport;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setNickName("chulung");
        webSessionSupport.logIn(user);
        for (int i = 0; i < 50; i++) {
            publish = new ArticleDraft();
            publish.setTitle("title" + i);
            publish.setTypeId(i % 4);
            publish.setContent("content");
            publish.setHtmlContent("<p>asdasd<p>");
            publish.setTags("aaa,bbb,ccc");
            publish.setIsPublish(PublishStatusEnum.Y);
        }
        this.articleService.insert(publish);
        notPublish = new ArticleDraft();
        PropertyUtils.copyProperties(notPublish, publish);
        this.articleService.insert(notPublish);
        publish.setTypeId(4);
        this.articleService.insert(publish);
    }

    @Test
    public void findArticleById() throws Exception {
        assertThat(this.articleService.findArticleById(1)).isNotNull();
    }

    @Test
    public void update() throws Exception {
        ArticleDraft articleDraft = this.articleService.findArticleDraft(publish.getId());
        assertThat(articleDraft.getContent()).isEqualTo("content");
        articleDraft.setContent("111");
        assertThat(this.articleService.update(articleDraft)).isTrue();
        articleDraft = this.articleService.findArticleDraft(publish.getId());
        assertThat(articleDraft.getContent()).isEqualTo("111");
    }

    @Test
    @Rollback
    public void deleteArticleDraft() throws Exception {
        this.articleService.deleteArticleDraft(publish.getId());
        assertThat(this.articleService.findArticleById(publish.getId()).getIsDelete()).isEqualTo(IsDeleteEnum.Y);
        assertThat(this.articleService.findArticleDraft(publish.getId()).getIsDelete()).isEqualTo(IsDeleteEnum.Y);
    }

    @Test
    public void findArticlePage() throws Exception {
        PageOut<ArticleOut> pageOut = this.articleService.findArticlePage(1, null);
        assertThat(pageOut.getList()).isNotEmpty();
        pageOut.getList().forEach(articleOut -> {
            assertThat(articleOut.getTypeId()).isNotEqualTo(4);
        });
        pageOut = this.articleService.findArticlePage(1, 1);
        assertThat(pageOut.getList()).isNotEmpty();
        pageOut.getList().forEach(articleOut -> assertThat(articleOut.getTypeId()).isEqualTo(1));
    }

    @Test
    public void getArticlesByYearMonth() throws Exception {
        LocalDate localDate = LocalDate.now();
        assertThat(this.articleService.getArticlesByYearMonth(localDate.getYear(), localDate.getMonthValue())).isNotEmpty();
    }

    @Test
    public void getArticleFilings() throws Exception {
        assertThat(this.articleService.getArticleFilings()).isNotEmpty();
    }

    @Test
    public void findRecommendedArticles() throws Exception {
        configService.getValueBykey(ConfigKeyEnum.RECOMMENDED_ARTICLE_IDS, publish.getId().toString());
        assertThat(this.articleService.findRecommendedArticles().size()).isEqualTo(1);
    }

    @Test
    public void findArticleDraftsList() throws Exception {
        assertThat(articleService.findArticleDraftsList(new PageIn<ArticleDraft>(1, 10, new ArticleDraft()))).isNotEmpty();
    }

    @Test
    public void getArticlesByTagName() throws Exception {
        assertThat(this.articleService.getArticlesByTagName("aaa")).isNotEmpty();
    }

    @Test
    public void listRelevancy() throws Exception {
        assertThat(this.articleService.findRelevancyByArticleId(publish.getId())).isNotEmpty();
    }

}
