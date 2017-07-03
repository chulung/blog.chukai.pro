package com.chulung;

import com.chulung.test.BaseTest;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public abstract class SpringbootBaseTest extends BaseTest {
}
