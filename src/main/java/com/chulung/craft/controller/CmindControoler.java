package com.chulung.craft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/cmind")
@RestController
public class CmindControoler extends BaseController {

	@RequestMapping("")
	public ModelAndView index() {
		return modelAndView("cmind");
	}
}
