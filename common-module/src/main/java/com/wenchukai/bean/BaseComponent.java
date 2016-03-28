package com.wenchukai.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected void errorLog(Throwable e) {
		this.logger.error("", e);
	}
}
