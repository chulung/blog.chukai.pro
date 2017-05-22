package com.chulung.website.interceptor;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chulung.website.tracker.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chulung.website.constant.Constants;

/**
 * 全局tracker 拦截器 拦截所有请求，包括搜索引擎
 * 
 * @author chulung
 *
 */
@Component
public class GlobalTrackerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Tracker tracker;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		validateSessionId((HttpServletRequest) request, (HttpServletResponse) response);
		tracker.track(request);
		return super.preHandle(request, response, handler);
	}

	private void validateSessionId(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constants.TUID.equals(cookie.getName())) {
					return;
				}
			}
		}
		Cookie cookie = new Cookie(Constants.TUID, UUID.randomUUID().toString());
		cookie.setDomain("chulung.com");
		cookie.setPath("/");
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
	}
}
