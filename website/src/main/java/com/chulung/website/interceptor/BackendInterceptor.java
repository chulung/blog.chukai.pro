package com.chulung.website.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chulung.website.session.WebSessionSupport;

/**
 * backend登录拦截器
 * 
 * @author ChuKai
 *
 */
@Component
public class BackendInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private WebSessionSupport webSessionSupport;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (webSessionSupport.islogIn()) {
			return super.preHandle(request, response, handler);
		} else {
			response.sendRedirect("/backend/logIn?reUrl=" + request.getRequestURL() + "?" + request.getQueryString());
			return false;
		}
	}

}
