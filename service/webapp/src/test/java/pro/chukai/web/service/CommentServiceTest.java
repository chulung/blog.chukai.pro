package pro.chukai.web.service;

import pro.chukai.test.SpringbootBaseTest;
import pro.chukai.web.exception.HttpStatusException;
import pro.chukai.web.model.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pro.chukai.web.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chukai on 2017/4/6.
 */
public class CommentServiceTest extends SpringbootBaseTest {
    @Autowired
    private CommentService commentService;

    @Test(expected = HttpStatusException.class)
    public void postComments() throws Exception {
        Comment comment = new Comment();
        comment.setArticleId(1);
        comment.setEmail("abc@qq.com");
        comment.setUserName("name");
        assertThat(commentService.postComments(comment)).isTrue();
        commentService.postComments(comment);
    }

    @Test
    public void listComments() throws Exception {
        assertThat(commentService.listComments(1, 1).getList()).isNotEmpty();
    }

    @Test
    public void findRecentlyComments() throws Exception {
        assertThat(commentService.findRecentlyComments()).isNotEmpty();
    }

}