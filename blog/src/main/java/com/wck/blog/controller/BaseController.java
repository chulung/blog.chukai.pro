package com.wck.blog.controller;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.wck.bean.BaseComponent;
import com.wck.blog.util.WebSessionSupport;

public class BaseController extends BaseComponent {

	@Resource
	protected WebSessionSupport webSessionSupport;

	private String staticsPath = "http://wenchukai.github.io/statics";

	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}

	public ModelMap errorMap() {
		return new ModelMap().addAttribute("success", 0);
	}

	public ModelAndView modelAndView(String path) {
		return new ModelAndView(path).addObject("staticsPath", staticsPath).addObject("auth",
				webSessionSupport.getCurUserAuthority());
	}
}
