package com.chulung.craft.service.impl;

import com.chulung.craft.enumerate.ConfigKeyEnum;
import com.chulung.craft.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.craft.mapper.ConfigMapper;
import com.chulung.craft.model.Config;

import javax.persistence.Transient;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public String getValueBykey(ConfigKeyEnum key) {
        Config record = configMapper.selectByPrimaryKey(key);
        return record == null ? null : record.getConfigValue();
    }

    @Override
    public Config create(Config config) {
        this.configMapper.insertSelective(config);
        return config;
    }

    @Override
    public String getValueBykey(ConfigKeyEnum key, String defaultV) {
        String value = this.getValueBykey(key);
        return value != null ? value : this.create(new Config(key, value)).getConfigValue();
    }

    @Override
    @Transient
    public boolean updateByKey(Config config) {
        return this.configMapper.updateByPrimaryKeySelective(config)>0;
    }

}
