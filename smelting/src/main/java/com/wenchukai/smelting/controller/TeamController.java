package com.wenchukai.smelting.controller;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wenchukai.smelting.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController extends BaseController {
	@Resource
	private TeamService teamService;

	@Override
	public ModelAndView modelAndView(String pageName) {
		return super.modelAndView(pageName).addObject("pageName", "team");
	}

	@RequestMapping(value = { "/", "" })
	public ModelAndView getAllTeam() {
		return modelAndView("/team/teams").addObject("teams", teamService.getAllTeams()).addObject("hasTeam",
				this.teamService.hasTeam(this.webSessionSupport.getCurPlayerId()));
	}

	@RequestMapping("/{id}")
	public ModelAndView get(@PathVariable Integer id) {
		return modelAndView("/team/team").addObject("team", teamService.getTeamById(id)).addObject("players");
	}

	@RequestMapping("/join")
	public ModelMap join(@RequestParam Integer id) {
		teamService.joinTeam(id);
		return successMap();
	}
}
