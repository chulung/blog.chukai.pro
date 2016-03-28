package com.wenchukai.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping(value={"","/","index.html"})
	public ModelAndView getIndex() {
		return modelAndView("markdown").addObject("typeId", 0);
	}

}
