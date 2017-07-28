package com.chulung.website.controller;

import com.chulung.common.util.JsonUtil;
import com.chulung.common.util.NetUtil;
import com.chulung.test.SpringBootMvcTest;
import com.chulung.website.model.Comment;
import org.junit.Test;
import org.springframework.http.MediaType;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by chulung on 2017/5/27.
 */
public class CommentsControllerTest extends SpringBootMvcTest {

    @Test
    public void testPost() throws Exception {
        Comment comment = new Comment();
        comment.setEmail("das");
        comment.setUserName("chul");
        comment.setArticleId(1);
        comment.setUri("sdasd");
        comment.setComment("sdas");
        String json = JsonUtil.toJsonString(comment);
        mockMvc.perform(post("/api/comments").cookie(new Cookie(NetUtil.SESSION_ID, "abc")).contentType(MediaType.APPLICATION_JSON).content(json).param("asd", "asds")).andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/comments").param("page", "1").param("articleId", "1")).andExpect(status().isOk());
    }

}