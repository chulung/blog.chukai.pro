package pro.chukai.web.interceptor;

import pro.chukai.common.util.NetUtil;
import pro.chukai.test.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by chukai on 2017/6/17.
 */
@PrepareForTest(value = {NetUtil.class, RequestContextHolder.class})
public class CookieInterceptorTest extends BaseTest {
    static HttpServletRequest request;

    @Before
    public void beforeClass() {
        mockStatic(NetUtil.class);
        mockStatic(RequestContextHolder.class);
        request = mock(HttpServletRequest.class);
        ServletRequestAttributes context = new ServletRequestAttributes(request);
        when(RequestContextHolder.getRequestAttributes()).thenReturn(context);
    }

    @Test
    public void preHandle() {
        try {
            when(request.getHeader(NetUtil.USER_AGENT)).thenReturn("windows");
            new CookieInterceptor().preHandle(request, mock(HttpServletResponse.class), mock(Object.class));
        } catch (Exception e) {
            fail("" + e);
        }
    }

    @Test
    public void isSpider() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        String[] ua = "baiduspider|googlebot|soso|bing|sogou|yahoo|sohu-search|yodao|YoudaoBot|robozilla|msnbot|MJ12bot|NHN|Twiceler".split("\\|");
        for (int i = 0; i < ua.length; i++) {
            when(request.getHeader(NetUtil.USER_AGENT)).thenReturn(ua[i]);
            assertThat(CookieInterceptor.isSpider(request)).isTrue();
        }
    }


}