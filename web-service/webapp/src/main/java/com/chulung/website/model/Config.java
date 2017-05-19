package com.chulung.website.model;

import com.chulung.website.enumerate.ConfigKeyEnum;

import javax.persistence.Id;

public class Config {


    private static final long serialVersionUID = 1126303022719698976L;

    @Id
    private ConfigKeyEnum configKey;

    private String configValue;

    public Config(ConfigKeyEnum key, String value) {
        this.configKey=key;
        this.configValue=value;
    }

    public Config() {
    }
    public ConfigKeyEnum getConfigKey() {
        return configKey;
    }

    public void setConfigKey(ConfigKeyEnum configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

}