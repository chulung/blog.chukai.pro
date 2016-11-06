package com.chulung.craft.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.chulung.craft.model.UserTracker;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chulung.craft.service.VisitorInfoService;

@RestController
@RequestMapping("/tracker")
public class TrackerController extends BaseController {
	@Resource
	private VisitorInfoService visitorInfoService;

	@RequestMapping("/userTracker")
	public ModelMap postUserTracker(UserTracker userTracker, HttpServletRequest request) {
		visitorInfoService.insertUserTracker(userTracker);
		return successMap();
	}

}
