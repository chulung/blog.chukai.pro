package com.chulung.website.controller;

import com.chulung.common.util.NetUtil;
import com.chulung.test.SpringBootMvcTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.Cookie;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by chulung on 2017/5/27.
 */
public class CommentsControllerTest extends SpringBootMvcTest {

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post("/api/comments").cookie(new Cookie(NetUtil.SESSION_ID, "abc")).param("comment", "abc").param("userName", "chulung").param("articleId", "1")).
                andExpect(status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/comments").param("page", "1").param("articleId", "1")).andExpect(status().isOk());
    }

}