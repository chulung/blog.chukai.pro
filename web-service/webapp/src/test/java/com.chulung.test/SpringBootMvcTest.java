package com.chulung.test;

import com.chulung.Application;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by chulung on 2017/5/26.
 */

@PowerMockRunnerDelegate(SpringRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = Application.class)
@Rollback
@PowerMockIgnore( {"javax.management.*"})
public class SpringBootMvcTest extends BaseTest implements ApplicationContextAware {
    protected MockMvc mockMvc;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        mockMvc= MockMvcBuilders.webAppContextSetup((WebApplicationContext) applicationContext).build();
    }
}
