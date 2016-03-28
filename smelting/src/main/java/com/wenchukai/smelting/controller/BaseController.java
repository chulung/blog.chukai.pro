package com.wenchukai.smelting.controller;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.wenchukai.bean.BaseComponent;
import com.wenchukai.smelting.util.WebSessionSupport;

public class BaseController extends BaseComponent {

	@Resource
	protected WebSessionSupport webSessionSupport;

	/**
	 * 静态分离处理,开发时指向本地,线上指向github的静态路径
	 */
	private String staticsPath = "/smelting/statics";
//	private String staticsPath="http://wenchukai.github.io/statics";

	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}

	public ModelMap errorMap() {
		return new ModelMap().addAttribute("success", 0);
	}

	public ModelAndView modelAndView(String pageName) {
		return new ModelAndView(pageName).addObject("staticsPath", staticsPath).addObject("auth",
				webSessionSupport.getCurPlayerAuthority());
	}
}
