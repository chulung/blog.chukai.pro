package com.chulung.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.blog.mapper.CikiMapper;
import com.chulung.blog.model.Ciki;
import com.chulung.blog.service.CikiService;

@Service
public class CikiServiceImpl implements CikiService {

	@Autowired
	private CikiMapper cikiMapper;
	@Override
	public List<Ciki> getCikiTitelListByParentId(Integer parentId) {
		Ciki record = new Ciki();
		record.setParentId(parentId);
		return cikiMapper.getCikiTitelListByParentId(record);
	}

}
