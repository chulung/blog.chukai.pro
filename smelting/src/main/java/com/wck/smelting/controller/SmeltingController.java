package com.wck.smelting.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wck.smelting.service.TeamService;
import com.wck.smelting.service.PlayerService;
import com.wck.smelting.service.SmeltingService;
import com.wck.smelting.service.TaskService;

@RestController
@RequestMapping("/")
public class SmeltingController extends BaseController {

	@Resource
	private SmeltingService smeltingService;
	@Resource
	private PlayerService playerService;
	@Resource
	private TeamService teamService;
	@Resource
	private TaskService taskService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/", "/index.html" }, method = RequestMethod.GET)
	public ModelAndView getIndex() {
		return modelAndView("taskList").addObject("pageName", "task").addObject("curTasks",
				this.taskService.getCurTasks()).addObject("expireTasks", this.taskService.getExpireTasks());
	}

	@RequestMapping(value = { "/point" }, method = RequestMethod.GET)
	public ModelAndView getPoint() {
		return modelAndView("point").addObject("pageName", "point")
				.addObject("players", this.playerService.getTopThreePlayer())
				.addObject("teams", teamService.getAllTeamsAndPlayer());
	}

}
