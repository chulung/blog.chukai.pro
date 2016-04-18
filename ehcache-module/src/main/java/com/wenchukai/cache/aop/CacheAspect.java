package com.wenchukai.cache.aop;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wenchukai.cache.CCache;
import com.wenchukai.cache.annotation.Cache;
import com.wenchukai.cache.annotation.ClearCacheAfter;

/**
 * 处理缓存aop
 * 
 * @author chukai
 *
 */
@Aspect
public class CacheAspect {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private CCache ccache;
	private boolean disabled = false;

	public CacheAspect() {
		this.logger.debug("Cache Aspect");
	}

	@Pointcut("execution(@com.wenchukai.cache.annotation.Cache * *(..))")
	public void cachePointCut() {
	}

	@Pointcut("execution(@com.wenchukai.cache.annotation.ClearCacheAfter * *(..))")
	public void ClearCacheAfterPointCut() {
	}

	/**
	 * 在方法执行后清空对应key缓存
	 * 
	 * @param joinPoint
	 */
	@After("ClearCacheAfterPointCut()")
	public void after(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		ClearCacheAfter clearCacheAfter = methodSignature.getMethod().getAnnotation(ClearCacheAfter.class);
		for (String key : clearCacheAfter.keys()) {
			logger.debug("clear cache key={}",key);
			this.ccache.remove(key);
		}
	}

	/**
	 * 如果目标方法已缓存返回值，则直接返回缓存值，否则调用目标方法并缓存返回值
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("cachePointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		if (disabled) {
			return joinPoint.proceed();
		}
		Cache cache = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Cache.class);
		Object object = this.ccache.get(cache.key());
		if (object != null) {
			logger.debug("get cache key={}",cache.key());
			return object;
		}
		object = joinPoint.proceed();

		if (cache.timeToLive() > 0) {
			this.ccache.put(cache.key(), object, 60 * cache.timeToLive());
		} else {
			throw new IllegalArgumentException("timeToLive must >0!");
		}
		return object;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
