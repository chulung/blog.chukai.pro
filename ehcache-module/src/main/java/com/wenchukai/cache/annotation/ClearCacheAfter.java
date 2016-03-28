package com.wenchukai.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解在方法上，在方法执行后清空对应key缓存
 * 
 * @author chukai
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface ClearCacheAfter {
	public String[]keys() default {};
}
