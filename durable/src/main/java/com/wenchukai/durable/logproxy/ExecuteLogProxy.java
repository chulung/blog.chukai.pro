package com.wenchukai.durable.logproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wenchukai.durable.core.Execute;

public class ExecuteLogProxy implements InvocationHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Execute execute;

	public Execute newExecuteLogProxy(Execute execute) {
		this.execute = execute;
		return (Execute) Proxy.newProxyInstance(execute.getClass().getClassLoader(), execute.getClass().getInterfaces(),
				this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();
		if (!name.equals("executeQuery") && !name.equals("executeUpdate")) {
			return method.invoke(execute, args);
		}
		long start = System.currentTimeMillis();
		Object invoke = method.invoke(execute, args);
		logger.debug("{}:{},consuming :{},\n params:{}", name, args[0], System.currentTimeMillis() - start, args[1]);
		return invoke;
	}
}
