package com.chulung.metaclblog.struct;

import java.util.Map;

/**
 * 
 * @author chulung1
 *
 */
public final class Source extends Struct<Source> {

	private String name;
	private String url;
	
	public Source() {
	}
	public Source(Map<String, Object> Params) {
		super(Params);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
