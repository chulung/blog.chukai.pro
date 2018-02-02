package pro.chukai.web.service;

import pro.chukai.common.util.NetUtil;
import pro.chukai.test.SpringbootBaseTest;
import pro.chukai.web.dto.out.ArticleOut;
import pro.chukai.web.dto.out.PageOut;
import pro.chukai.web.enumerate.IsDeleteEnum;
import pro.chukai.web.enumerate.PublishStatusEnum;
import pro.chukai.web.exception.HttpStatusException;
import pro.chukai.web.mapper.ArticleMapper;
import pro.chukai.web.model.ArticleDraft;
import pro.chukai.web.model.User;
import pro.chukai.web.session.WebSessionSupport;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import pro.chukai.web.enumerate.IsDeleteEnum;
import pro.chukai.web.model.User;
import pro.chukai.web.session.WebSessionSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Created by chukai on 2017/3/21.
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
        user.setNickName("chukai");
        PowerMockito.mockStatic(NetUtil.class);
        PowerMockito.when(NetUtil.getCurSessionId()).thenReturn("sessionid");
        webSessionSupport.logIn(user);
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
