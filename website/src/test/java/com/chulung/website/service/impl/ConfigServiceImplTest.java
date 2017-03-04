package com.chulung.website.service.impl;

import com.chulung.Application;
import com.chulung.test.BaseTest;
import com.chulung.website.enumerate.ConfigKeyEnum;
import com.chulung.website.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


public class ConfigServiceImplTest extends BaseTest{

    @Autowired
    private ConfigService configService;
    @Test
    public void getValueBykey() throws Exception {
    }

    @Test
    public void create() throws Exception {

    }

    public void getValueBykey1() throws Exception {

    }

    @Test
    public void updateByKey() throws Exception {

    }

}