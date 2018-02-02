package pro.chukai.web.dto;

import pro.chukai.web.dto.in.ArticleIn;
import pro.chukai.web.dto.out.ArticleOut;
import pro.chukai.web.model.Article;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseDtoTest {

    private Article article;

    @Before
    public void before() {
        this.article = new Article();
        article.setId(1);
        article.setAuthor("cl");
        article.setCreateTime(LocalDateTime.now());
    }

    @Test
    public void testBuildFromModel() {
        ArticleOut out = new ArticleOut().buildFromModel(article);
        assertThat(out).isEqualToIgnoringNullFields(article);
    }

    @Test
    public void testBuildModel() {
        Article in = new ArticleIn().buildFromModel(article).buildModel();
        assertThat(in).isEqualToIgnoringNullFields(article);
    }
}