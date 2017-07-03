package com.chulung.website.interceptor;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chulung.common.util.NetUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * @author chulung
 */
@Component
public class CookieInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        validateSessionId((HttpServletRequest) request, (HttpServletResponse) response);
        return super.preHandle(request, response, handler);
    }

    private void validateSessionId(HttpServletRequest request, HttpServletResponse response) {
        if (NetUtil.getCurSessionId() != null || isSpider(request)) {
            return;
        }
        Cookie cookie = new Cookie(NetUtil.SESSION_ID, UUID.randomUUID().toString());
        cookie.setDomain(NetUtil.getPrimaryDomain());
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
    }

    public static boolean isSpider(HttpServletRequest request) {
        String ua = NetUtil.getUserAgent();
        return ua == null || ua.matches("baiduspider|googlebot|soso|bing|sogou|yahoo|sohu-search|yodao|YoudaoBot|robozilla|msnbot|MJ12bot|NHN|Twiceler");
    }

}
