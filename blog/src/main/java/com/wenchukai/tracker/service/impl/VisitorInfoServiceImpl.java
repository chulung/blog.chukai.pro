package com.wenchukai.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenchukai.tracker.mapper.VisitorInfoMapper;
import com.wenchukai.tracker.model.VisitorInfo;
import com.wenchukai.tracker.service.VisitorInfoService;

@Service
public class VisitorInfoServiceImpl implements VisitorInfoService{
	@Autowired
	private VisitorInfoMapper visitorMapper;
	@Override
	public void insertVisitorInfo(VisitorInfo visitorInfo) {
		visitorMapper.insert(visitorInfo);
	}

}
