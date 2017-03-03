package com.chulung.website.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * markdown 编辑器
 * @author ChuKai
 *
 */
@RequestMapping("/markdown")
@RestController
public class MarkdownController extends BaseController{
	
	/**
	 * 编辑器首页
	 * @return
	 */
	@RequestMapping(value={"","/","index.html"}, method = RequestMethod.GET)
	public ModelAndView getIndex() {
		return modelAndView("markdown").addObject("typeId", 0);
	}

}
