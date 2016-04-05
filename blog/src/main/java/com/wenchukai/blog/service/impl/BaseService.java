package com.wenchukai.blog.service.impl;

import com.wenchukai.base.BaseComponent;
import com.wenchukai.util.StringUtil;

public abstract class BaseService extends BaseComponent {

	protected boolean checkExistBlank(Object ... params) {
		for (Object param : params) {
			if (param == null ? true : param instanceof String ? StringUtil.isBlank(param.toString()) : false) {
				return true;
			}
		}
		return false;
	}
}
