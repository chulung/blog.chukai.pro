package pro.chukai.web.service;

import org.assertj.core.api.Assertions;
import pro.chukai.common.util.NetUtil;
import pro.chukai.test.SpringbootBaseTest;
import pro.chukai.web.dto.in.UserIn;
import pro.chukai.web.exception.HttpStatusException;
import pro.chukai.web.mapper.UserMapper;
import pro.chukai.web.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pro.chukai.web.dto.in.UserIn;
import pro.chukai.web.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by chukai on 2017/7/9.
 */

@PrepareForTest(NetUtil.class)
public class UserServiceImplTest extends SpringbootBaseTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void logInbackendWithIllegalParams() {
        UserIn user = new UserIn();
        try {
            userService.logInbackend(user);
        } catch (HttpStatusException e) {
            assertThat(e.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
            return;
        }
        fail();
    }

    @Test
    public void logInbackendWithRegisterFail() {
        UserIn user = new UserIn();
        user.setRegister(true);
        user.setPassword("1111");
        user.setNickName("sdasd");
        user.setUserName("asb");
        try {
            userService.logInbackend(user);
        } catch (HttpStatusException e) {
            assertThat(e.getStatus()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
            return;
        }
        fail();
    }

    @Test
    public void logInbackendWithRegisterSucess() {
        mockStatic(NetUtil.class);
        when(NetUtil.getCurSessionId()).thenReturn("sdasdas");
        userMapper.delete(new User());
        UserIn user = new UserIn();
        user.setRegister(true);
        user.setPassword("1111");
        user.setNickName("sdasd");
        user.setUserName("asb");
        Assertions.assertThat(userService.logInbackend(user)).isNotNull();
        user.setRegister(false);
        Assertions.assertThat(userService.logInbackend(user)).isNotNull();
    }

}