package com.chulung.website.controller;

import com.chulung.test.SpringBootMvcTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by chulung on 2017/5/26.
 */
public class ArticleControllerTest extends SpringBootMvcTest {


    @Test
    public void getArticleById() throws Exception {
        mockMvc.perform(get("/api/article/{id}", "uri")).andExpect(status().isOk());
    }

    @Test
    public void relevancy() throws Exception {
        mockMvc.perform(get("/api/article/relevancy/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    public void getArticlePage() throws Exception {
        mockMvc.perform(get("/api/articles")).andExpect(status().isOk());
    }

    @Test
    public void getArticlesByTag() throws Exception {
        mockMvc.perform(get("/api/tag").param("tag", "java")).andExpect(status().isOk());
    }

}