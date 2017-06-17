package com.chulung.common.util;

import com.chulung.test.BaseTest;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by chulung on 2017/6/17.
 */

public class NetUtilTest extends BaseTest{
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
        HttpServletRequest request=mock(HttpServletRequest.class);
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