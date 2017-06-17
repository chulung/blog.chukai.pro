package com.chulung.website.interceptor;

import com.chulung.common.util.NetUtil;
import com.chulung.test.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by chulung on 2017/6/17.
 */
@PrepareForTest(NetUtil.class)
public class CookieInterceptorTest extends BaseTest {
    @BeforeClass
    public static void beforeClass() {
        mockStatic(NetUtil.class);
        when(NetUtil.getRequest()).thenReturn(mock(HttpServletRequest.class));
    }

    @Test
    public void preHandle() throws Exception {
        new CookieInterceptor().preHandle(mock(HttpServletRequest.class), mock(HttpServletResponse.class), mock(Object.class));
    }

    @Test
    public void isSpider() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        String[] ua = "baiduspider|googlebot|soso|bing|sogou|yahoo|sohu-search|yodao|YoudaoBot|robozilla|msnbot|MJ12bot|NHN|Twiceler".split("\\|");
        for (int i = 0; i < ua.length; i++) {
            when(NetUtil.getUserAgent()).thenReturn(ua[i]);
            assertThat(CookieInterceptor.isSpider(request)).isTrue();
        }
    }

}