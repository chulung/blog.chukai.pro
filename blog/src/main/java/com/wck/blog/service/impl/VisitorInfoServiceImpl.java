package com.wck.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wck.blog.service.VisitorInfoService;
import com.wck.durable.core.Session;
import com.wck.tracker.bean.VisitorInfo;

@Service
public class VisitorInfoServiceImpl implements VisitorInfoService{
	@Resource
	private Session sessionNoLog;
	@Override
	public void insertVisitorInfo(VisitorInfo visitorInfo) {
		sessionNoLog.insert(visitorInfo);
	}

}
