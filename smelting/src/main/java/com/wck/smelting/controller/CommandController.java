package com.wck.smelting.controller;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wck.smelting.service.CommandService;

@RestController
@RequestMapping("/command")
public class CommandController extends BaseController {
	@Resource
	private CommandService commandService;

	@Override
	public ModelAndView modelAndView(String pageName) {
		return super.modelAndView(pageName).addObject("pageName", "command");
	}

	@RequestMapping("")
	public ModelAndView command() {
		return this.modelAndView("/command");

	}

	@RequestMapping("/execute/{command}")
	public ModelMap executeCommand(@PathVariable String command) {
		this.commandService.excute(command);
		return successMap();
	}
}
