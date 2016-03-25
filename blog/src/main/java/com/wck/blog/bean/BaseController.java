package com.wck.blog.bean;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.wck.bean.BaseComponent;
import com.wck.blog.util.WebSessionSupport;

public class BaseController extends BaseComponent {

	@Resource
	protected WebSessionSupport webSessionSupport;

	private String baseStaticsPath = "http://wenchukai.github.io/statics";
	/**
	 * 静态分离处理,开发时指向本地,线上指向github的静态路径
	 */
	// private String staticsPath = "/statics";
	private String staticsPath = "http://wenchukai.github.io/statics";

	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}

	public ModelMap errorMap() {
		return new ModelMap().addAttribute("success", 0);
	}

	public ModelAndView modelAndView(String path) {
		return new ModelAndView(path).addObject("staticsPath", staticsPath)
				.addObject("baseStaticsPath", baseStaticsPath).addObject("auth",

						webSessionSupport.getCurUserAuthority());
	}
}
