package com.chulung.blog.service.impl;

import com.chulung.blog.common.util.StringUtil;
import com.chulung.blog.exception.GlobalMethodRuntimeExcetion;
import com.chulung.blog.model.BaseComponent;

public abstract class BaseService extends BaseComponent {

	protected void checkExistBlank(Object... params) {
		for (Object param : params) {
			if (param == null ? true : param instanceof String ? StringUtil.isBlank(param.toString()) : false) {
				logger.error(StringUtil.join(params, ','));
				throw new GlobalMethodRuntimeExcetion("必填参数为空");
			}
		}
	}
}
