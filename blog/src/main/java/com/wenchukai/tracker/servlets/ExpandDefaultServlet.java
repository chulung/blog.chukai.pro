package com.wenchukai.tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.wenchukai.tracker.Tracker;

/**
 * 重写tomcat 默认servlet 从而拦截静态资源请求
 * 
 * @author chukai
 *
 */
public class ExpandDefaultServlet extends DefaultServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8689478541569407685L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Tracker.track(request);
		super.doGet(request, response);
	}
}
