package com.chulung.common.util;

import com.chulung.test.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by chulung on 2017/6/17.
 */
@PrepareForTest(value = {RequestContextHolder.class})
public class NetUtilTest extends BaseTest {
    HttpServletRequest request = mock(HttpServletRequest.class);

    @Before
    public void before() {
        mockStatic(RequestContextHolder.class);
        request = mock(HttpServletRequest.class);
        when(RequestContextHolder.getRequestAttributes()).thenReturn(new ServletRequestAttributes(request));
    }

    @Test
    public void getIpAddr() throws Exception {

    }

    @Test
    public void getCookieValue() throws Exception {

    }

    @Test
    public void getCurSessionId() throws Exception {

    }

    @Test
    public void getPrimaryDomain() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://chulung.com"));
        assertThat(NetUtil.getPrimaryDomain()).isEqualTo("chulung.com");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://blog.chulung.com"));
        assertThat(NetUtil.getPrimaryDomain()).isEqualTo("chulung.com");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://abc.blog.chulung.com"));
        assertThat(NetUtil.getPrimaryDomain()).isEqualTo("chulung.com");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://blog.chulung.com/asdasd?a=http://aaa.com"));
        assertThat(NetUtil.getPrimaryDomain()).isEqualTo("chulung.com");

    }

}