package com.wenchukai.smelting.service;

import javax.annotation.Resource;

import com.wenchukai.bean.BaseComponent;
import com.wenchukai.durable.core.Session;
import com.wenchukai.smelting.util.WebSessionSupport;
import com.wenchukai.util.StringUtil;

public abstract class BaseService extends BaseComponent {
	@Resource
	protected WebSessionSupport webSessionSupport;
	@Resource
	protected Session session;

	protected boolean checkExistBlank(Object ... params) {
		for (Object param : params) {
			if (param == null ? true : param instanceof String ? StringUtil.isBlank(param.toString()) : false) {
				return true;
			}
		}
		return false;
	}
}
