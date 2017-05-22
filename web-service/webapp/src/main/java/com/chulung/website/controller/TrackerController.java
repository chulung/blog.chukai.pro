package com.chulung.website.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.chulung.website.model.UserTracker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chulung.website.service.VisitorInfoService;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController extends BaseController {
    @Resource
    private VisitorInfoService visitorInfoService;

    @RequestMapping(value = "/userTracker", method = RequestMethod.POST)
    public void postUserTracker(UserTracker userTracker, HttpServletRequest request) {
        visitorInfoService.insertUserTracker(userTracker);
    }

}
