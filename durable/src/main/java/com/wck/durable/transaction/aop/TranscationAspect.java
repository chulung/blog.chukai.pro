package com.wck.durable.transaction.aop;

import java.lang.reflect.Method;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wck.durable.core.Execute;
import com.wck.durable.transaction.annotation.Transaction;

/**
 * 用于注解事务的aop
 * 
 * @author ChuKai
 *
 */
@Aspect
public class TranscationAspect {
	@Resource
	private DataSource dataSource;
	private static Logger logger = LoggerFactory.getLogger(TranscationAspect.class);

	@Pointcut("execution(@com.wck.durable.transaction.annotation.Transaction * *(..))")
	public void transcationPointCut() {
	}

	@Around("transcationPointCut()")
	public Object aroud(ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Transaction transaction = method.getAnnotation(Transaction.class);
		if (!transaction.openTransaction()) {
			return joinPoint.proceed();
		}
		Connection connection = null;
		if ((connection = Execute.CONNECTION_THREAD_LOCAL.get()) == null) {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			Execute.CONNECTION_THREAD_LOCAL.set(connection);
		}
		try {
			Object object = joinPoint.proceed();
			connection.commit();
			return object;
		} catch (Throwable e) {
			connection.rollback();
			logger.error("transcation rollback method:{} ", method.getName());
			throw e;
		} finally {
			if (!connection.isClosed()) {
				connection.close();
			}
			Execute.CONNECTION_THREAD_LOCAL.remove();
		}
	}
}
