package com.chulung.website.service;

import com.chulung.test.SpringbootBaseTest;
import com.chulung.website.exception.HttpStatusException;
import com.chulung.website.model.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chulung on 2017/4/6.
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