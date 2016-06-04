package com.chulung.blog.service.impl;

import org.springframework.cache.annotation.CacheConfig;

import com.chulung.blog.exception.GlobalMethodRuntimeExcetion;
import com.chulung.common.base.BaseComponent;
import com.chulung.common.util.StringUtils;

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
