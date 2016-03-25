package com.wck.smelting.service;

import javax.annotation.Resource;

import com.wck.bean.BaseComponent;
import com.wck.durable.core.Session;
import com.wck.smelting.util.WebSessionSupport;
import com.wck.util.StringUtil;

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
