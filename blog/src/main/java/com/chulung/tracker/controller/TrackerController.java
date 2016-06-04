package com.chulung.tracker.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chulung.common.base.BaseController;
import com.chulung.tracker.model.UserTracker;
import com.chulung.tracker.service.VisitorInfoService;

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
