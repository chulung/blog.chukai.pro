package com.wck.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解与方法上，该方法返回值缓存
 * 
 * @author chukai
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Cache {
	public String key();
	
	/**
	 * 存活分钟
	 * @return
	 */
	public int timeToLive() default -1;
}
