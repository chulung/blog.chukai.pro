package com.wenchukai.smelting.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wenchukai.smelting.bean.Task;
import com.wenchukai.smelting.bean.TaskRecive;
import com.wenchukai.smelting.service.TaskService;

@RestController
@RequestMapping("/")
public class TaskController extends BaseController {
	@Resource
	private TaskService taskService;

	@Override
	public ModelAndView modelAndView(String pageName) {
		return super.modelAndView(pageName).addObject("pageName", "task");
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public ModelAndView post(@ModelAttribute Task task) {
		return this.get(this.taskService.insert(task));
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable Integer id) {
		return this.modelAndView("/task/task").addObject("task", this.taskService.getTaskById(id));
	}

	@RequestMapping(value = "/task/new", method = RequestMethod.GET)
	public ModelAndView newTask() {
		return this.modelAndView("/task/newTask");
	}

	@RequestMapping(value = "/task/receive", method = RequestMethod.POST)
	public ModelMap receive(@RequestParam Integer id) {
		this.taskService.receive(id);
		return successMap();
	}

	@RequestMapping(value = "/taskRecive/{id}", method = RequestMethod.POST)
	public ModelAndView taskRecive(@PathVariable Integer id) {
		return this.modelAndView("/task/taskRecive").addAllObjects(this.taskService.getTaskRecive(id));
	}

}
