package com.chulung.blog.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.chulung.blog.model.BaseComponent;
import com.chulung.common.util.StringUtil;

/**
 * Controller基类
 * 
 * @author ChuKai
 *
 */
public abstract class BaseController extends BaseComponent {

	private String staticsPath = "https://static.chulung.com/statics";

	/**
	 * 默认成功响应，用于json返回
	 * 
	 * @return
	 */
	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}

	/**
	 * 默认错误响应，用于json返回
	 * 
	 * @return
	 */
	public ModelMap errorMap() {
		return new ModelMap().addAttribute("success", 0);
	}

	/**
	 * 约定模块名为将当前Controller类名去除Controller后缀后首字母小写 默认模块名即页面文件名，和对应js文件名
	 * 
	 * @return
	 */
	public ModelAndView modelAndView() {
		String moduleName = this.getClass().getSimpleName();
		moduleName = StringUtil.toLowerCaseFirstChar(moduleName.substring(0, moduleName.length() - 10));
		return modelAndView(moduleName);
	}

	/**
	 * 视图名和模块名一致
	 * 
	 * @param moduleName
	 * @return
	 */
	public ModelAndView modelAndView(String moduleName) {
		return modelAndView(moduleName, moduleName);
	}

	/**
	 * 
	 * @param viewName
	 *            视图
	 * @param moduleName
	 *            模块名
	 * @return
	 */
	public ModelAndView modelAndView(String viewName, String moduleName) {
		return new ModelAndView(viewName).addObject("moduleName", moduleName).addObject("staticsPath", staticsPath);
	}

}
