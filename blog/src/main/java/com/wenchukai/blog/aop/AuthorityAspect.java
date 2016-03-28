package com.wenchukai.blog.aop;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.wenchukai.blog.annotation.Authority;
import com.wenchukai.blog.enumerate.AuthorityEnum;
import com.wenchukai.blog.exception.GlobalMethodRuntimeExcetion;
import com.wenchukai.blog.util.WebSessionSupport;

/**
 * 权限控制
 * 
 * @author ChuKai
 *
 */
@Aspect
public class AuthorityAspect {
	@Resource
	private WebSessionSupport websessionSupport;

	/**
	 * 切入所有请求方法前 排除get方法
	 */
	@Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..)) && !execution(* *.get*(..))")
	public void authorityPointCut() {
	}

	@Around("authorityPointCut()")
	public Object Around(ProceedingJoinPoint point) throws Throwable {
		Integer auth = AuthorityEnum.SUPERADMIN.getCode();
		Authority authority = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Authority.class);
		if (authority == null) {
			authority = point.getTarget().getClass().getAnnotation(Authority.class);
		}
		if (authority != null) {
			auth = authority.authority()[0].getCode();
		}
		if (hasAuthority(auth)) {
			return point.proceed();
		}
		throw new GlobalMethodRuntimeExcetion(0, "无权操作");
	}

	private boolean hasAuthority(Integer auth) {
		Integer curUserAuthority = websessionSupport.getCurUserAuthority();
		// 超级管理员,或者当前用户权限等级不小于要求等级
		return AuthorityEnum.SUPERADMIN.getCode().equals(curUserAuthority) || curUserAuthority >= auth;
	}

}
