package com.chulung.test;

import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@PowerMockRunnerDelegate(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public abstract class SpringbootBaseTest extends BaseTest {
    static {
        //使用测试环境配置 src/test/resources/application-test.yml
        System.setProperty("spring.profiles.active","test");
    }
}
