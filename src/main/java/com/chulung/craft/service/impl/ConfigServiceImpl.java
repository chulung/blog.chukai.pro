package com.chulung.craft.service.impl;

import com.chulung.craft.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.craft.mapper.ConfigMapper;
import com.chulung.craft.model.Config;

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
