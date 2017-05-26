package com.chulung.website.service;

import com.chulung.common.util.NetUtil;
import com.chulung.test.SpringbootBaseTest;
import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.enumerate.IsDeleteEnum;
import com.chulung.website.enumerate.PublishStatusEnum;
import com.chulung.website.exception.HttpStatusException;
import com.chulung.website.mapper.ArticleMapper;
import com.chulung.website.model.ArticleDraft;
import com.chulung.website.model.User;
import com.chulung.website.session.WebSessionSupport;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Created by chulung on 2017/3/21.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@PrepareForTest(NetUtil.class)
public class ArticleServiceTest extends SpringbootBaseTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper mapper;
    private ArticleDraft publish;
    private ArticleDraft notPublish;
    @Autowired
    private ColumnSevice columnSevice;

    @Autowired
    private ConfigService configService;

    @Autowired
    private WebSessionSupport webSessionSupport;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setNickName("chulung");
        PowerMockito.mockStatic(NetUtil.class);
        PowerMockito.when(NetUtil.getCookieValue(Mockito.anyString())).thenReturn(webSessionSupport.logIn(user));
        for (int i = 0; i < 50; i++) {
            publish = new ArticleDraft();
            publish.setTitle("title" + i);
            publish.setColumnId(i % 4 + 1);
            publish.setContent("content");
            publish.setHtmlContent("<p>asdasd<p>");
            publish.setTags("aaa,bbb,ccc");
            publish.setIsPublish(PublishStatusEnum.Y);
            this.articleService.insert(publish);
        }
        notPublish = new ArticleDraft();
        PropertyUtils.copyProperties(notPublish, publish);
        notPublish.setTitle("title name");
        notPublish.setId(null);
        notPublish.setId(this.articleService.insert(notPublish));
        publish.setColumnId(4);
        publish.setId(null);
        publish.setId(this.articleService.insert(publish));
    }

    @Test
    public void findArticleById() throws Exception {
        assertThat(this.articleService.findArticleByUri("uri")).isNotNull();
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
        HttpStatusException t = null;
        try {
            assertThat(this.articleService.findArticleById(publish.getArticleId()));
            fail("must throw HttpStatuExcetion");
        } catch (HttpStatusException e) {
            t = e;
        }
        assertThat(t.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(this.articleService.findArticleDraft(publish.getId()).getIsDelete()).isEqualTo(IsDeleteEnum.Y);
    }

    @Test
    public void findArticlePage() throws Exception {
        PageOut<ArticleOut> pageOut = this.articleService.findArticlePage(1, this.columnSevice.getIdColumnMap().get(1).getEnName(), null, null);
        assertThat(pageOut.getList()).isNotEmpty();
        pageOut.getList().forEach(articleOut -> {
            assertThat(articleOut.getColumnId()).isEqualTo(1);
        });
    }

    @Test
    public void getArticleFilings() throws Exception {
        assertThat(this.articleService.getArchive()).isNotEmpty();
    }

    @Test
    public void findRecommendedArticles() throws Exception {
        assertThat(this.articleService.findRecommendedArticles().size()).isEqualTo(1);
    }

    @Test
    public void findArticleDraftsList() throws Exception {
        assertThat(articleService.findArticleDraftsList(1, null).getList()).isNotEmpty();
    }

    @Test
    public void getArticlesByTagName() throws Exception {
        assertThat(this.articleService.getArticlesByTagName("aaa").getList()).isNotEmpty();
    }

    @Test
    public void listRelevancy() throws Exception {
        assertThat(this.articleService.findRelevancyByArticleId(publish.getArticleId())).isNotEmpty();
    }

}
