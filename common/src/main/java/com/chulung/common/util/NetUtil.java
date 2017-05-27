package com.chulung.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class NetUtil {

	public final static String SESSION_ID = "session_id";
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress;
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
																// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		}
		return ipAddress;
	}

	public static String getCookieValue(String name) {
		Cookie cookie = getCookie(name);
		return cookie == null ? null : cookie.getValue();
	}

	public static String getCurSessionId() {
		return NetUtil.getCookieValue(SESSION_ID);

	}

	private static Cookie getCookie(String name) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes == null) {
			return null;
		}
		HttpServletRequest request = servletRequestAttributes.getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static String getAccessUrl(HttpServletRequest request) {
		return new StringBuffer(request.getRequestURL().toString())
				.append(StringUtils.isNotBlank(request.getQueryString()) ? ("?" + request.getQueryString()) : "")
				.toString();
	}
}
