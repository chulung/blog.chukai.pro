package com.chulung.website.controller;

import com.chulung.mybatis.mapper.BaseMapper;
import com.chulung.test.SpringBootMvcTest;
import com.chulung.website.interceptor.AdminInterceptor;
import com.chulung.website.interceptor.GlobalTrackerInterceptor;
import com.chulung.website.mapper.*;
import com.chulung.website.model.Article;
import com.chulung.website.model.ArticleTag;
import com.chulung.website.model.VisitorInfo;
import com.chulung.website.service.*;
import com.chulung.website.session.WebSessionSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tk.mybatis.mapper.common.Mapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by chulung on 2017/5/26.
 */
public class ArticleControllerTest extends SpringBootMvcTest  {


    @Test
    public void getArticleById() throws Exception {
        mockMvc.perform(get("/api/article/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    public void relevancy() throws Exception {

    }

    @Test
    public void getArticlePage() throws Exception {

    }

    @Test
    public void getArticlesByTag() throws Exception {

    }

}