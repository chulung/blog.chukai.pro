package com.chulung.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.blog.mapper.ConfigMapper;
import com.chulung.blog.model.Config;
import com.chulung.blog.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigMapper configMapper;

	@Override
	public String getValueBykey(String key) {
		Config record = new Config();
		record.setConfigKey(key);
		record = configMapper.selectOne(record);
		return record == null ? null : record.getConfigValue();
	}

}
