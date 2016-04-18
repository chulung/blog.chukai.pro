package com.wenchukai.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用组件基类
 * @author ChuKai
 *
 */
public abstract class BaseComponent {
	//声明日志对象
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 只打印throwable 对象的错误日志方法
	 * @param e
	 */
	protected void errorLog(Throwable e) {
		this.logger.error("", e);
	}
}
