package com.wenchukai.blog.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
	@RequestMapping("/healthCheck")
	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}
}
