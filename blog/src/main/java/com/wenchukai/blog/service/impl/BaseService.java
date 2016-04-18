package com.wenchukai.blog.service.impl;

import com.wenchukai.blog.exception.GlobalMethodRuntimeExcetion;
import com.wenchukai.common.base.BaseComponent;
import com.wenchukai.common.util.StringUtils;

public abstract class BaseService extends BaseComponent {

	protected void checkExistBlank(Object... params) {
		for (Object param : params) {
			if (param == null ? true : param instanceof String ? StringUtils.isBlank(param.toString()) : false) {
				logger.error(StringUtils.join(params, ','));
				throw new GlobalMethodRuntimeExcetion("必填参数为空");
			}
		}
	}
}
