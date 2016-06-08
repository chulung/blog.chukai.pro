package com.chulung.metaclblog.struct;

import java.util.Map;

public final class Enclosure extends Struct<Enclosure>{
	

	private Integer length;
	private String type;
	private String url;
	public Enclosure() {
	}
	public Enclosure(Map<String, Object> Params) {
		super(Params);
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
