package com.chulung.website.controller;

import com.chulung.common.util.JsonUtil;
import com.chulung.common.util.NetUtil;
import com.chulung.test.SpringBootMvcTest;
import com.chulung.website.model.Comment;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.Cookie;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        mockMvc.perform(post("/api/comments").cookie(new Cookie(NetUtil.SESSION_ID, "abc")).contentType(MediaType.APPLICATION_JSON).content(json).param("asd","asds")).andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/comments").param("page", "1").param("articleId", "1")).andExpect(status().isOk());
    }

}