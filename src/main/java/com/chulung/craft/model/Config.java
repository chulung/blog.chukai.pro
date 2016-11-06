package com.chulung.craft.model;

import javax.persistence.Id;

public class Config extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1126303022719698976L;
	@Id
	private Integer id;

	private String configKey;

	private String configValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

}