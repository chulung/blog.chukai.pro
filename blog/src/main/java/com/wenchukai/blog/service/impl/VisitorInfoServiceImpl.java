package com.wenchukai.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenchukai.blog.service.VisitorInfoService;
import com.wenchukai.durable.core.Session;
import com.wenchukai.tracker.bean.VisitorInfo;

@Service
public class VisitorInfoServiceImpl implements VisitorInfoService{
	@Resource
	private Session sessionNoLog;
	@Override
	public void insertVisitorInfo(VisitorInfo visitorInfo) {
		sessionNoLog.insert(visitorInfo);
	}

}
