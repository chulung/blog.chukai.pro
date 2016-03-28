package com.wenchukai.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.wenchukai.bean.BaseComponent;
import com.wenchukai.blog.util.WebSessionSupport;

public class BaseController extends BaseComponent {

	@Resource
	protected WebSessionSupport webSessionSupport;

	private String staticsPath = "http://wenchukai.github.io/statics";

	private Map<String, String> excludedPathMap = new HashMap<>();

	public BaseController() {
		excludedPathMap = new HashMap<>();
		excludedPathMap.put("", "");
	}

	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}

	public ModelMap errorMap() {
		return new ModelMap().addAttribute("success", 0);
	}

	public ModelAndView modelAndView(String path) {
		return new ModelAndView(path).addObject("staticsPath", staticsPath)
				.addObject("auth", webSessionSupport.getCurUserAuthority())
				.addObject("excludedPathMap", excludedPathMap);
	}
}
