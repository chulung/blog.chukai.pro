package com.chulung.craft.controller;

import java.io.File;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ciki")
public class CikiController extends BaseController {
	private String rootPath = System.getProperty("website.root");

	@Override
	public ModelAndView modelAndView(String moduleName) {
		return super.modelAndView(moduleName, null).addObject("typeId", 5);
	}

	@RequestMapping(value = { "", "/" })
	public ModelAndView index() {
		return modelAndView("cikiIndex");
	}

	@RequestMapping("/{cate}/{title}")
	public ModelAndView getCiki(@PathVariable String cate, @PathVariable String title) {
		if (new File(String.format("%s/ciki/%s/%s.html", rootPath, cate, title)).exists()) {
			return modelAndView("cikiContext").addObject("cate", cate).addObject("title", title);
		}
		return null;
	}
}
