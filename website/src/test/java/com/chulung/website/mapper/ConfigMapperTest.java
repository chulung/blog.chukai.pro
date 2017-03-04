package com.chulung.website.mapper;

import com.chulung.Application;
import com.chulung.website.enumerate.ConfigKeyEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by chukai on 2017/3/4.
 */
/**
 * Created by chukai on 2017/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = Application.class)
public class ConfigMapperTest {

    @Autowired
    private  ConfigMapper configMapper;
    @Test
    public void insert(){
    }
}