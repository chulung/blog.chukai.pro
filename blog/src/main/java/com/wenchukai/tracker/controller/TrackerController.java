package com.wenchukai.tracker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenchukai.blog.controller.BaseController;
import com.wenchukai.tracker.model.UserTracker;
import com.wenchukai.tracker.service.VisitorInfoService;

@RestController
@RequestMapping("/tracker")
public class TrackerController extends BaseController {
	@Resource
	private VisitorInfoService visitorInfoService;

	@RequestMapping("/userTracker")
	public void postUserTracker(UserTracker userTracker, HttpServletRequest request) {
		visitorInfoService.insertUserTracker(userTracker);
	}

}
