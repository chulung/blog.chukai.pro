package com.wenchukai.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wenchukai.tracker.Tracker;

/**
 * tracker 拦截器
 * @author ChuKai
 *
 */
public class TrackerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Tracker.track(request);
		return super.preHandle(request, response, handler);
	}

}
