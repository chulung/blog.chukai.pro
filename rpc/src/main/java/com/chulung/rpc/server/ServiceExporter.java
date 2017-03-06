package com.chulung.rpc.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.chulung.common.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;

import com.chulung.rpc.core.RpcException;
import com.chulung.rpc.core.RpcRequest;
import com.chulung.rpc.core.RpcResponse;
import com.chulung.rpc.core.ServiceProfile;
import com.chulung.rpc.utils.ServicePathUtils;

public class ServiceExporter implements InitializingBean{
	
	private Object target;
	private Class<?> serviceInterface;
	private ServiceProfile serviceProfile;
	private Map<String,Method> methodMap = new HashMap<String,Method>();
	
	public void afterPropertiesSet() throws Exception {
		initMethodMap();
	}
	
	public void init() throws Exception {
		afterPropertiesSet();
	}
	
	protected void initMethodMap(){
		Method[] methods = serviceInterface.getDeclaredMethods();
		for(Method method : methods){
			methodMap.put(method.getName(), method);
		}
	}


	public RpcResponse invoke(RpcRequest request) {
		RpcResponse res = new RpcResponse();
		try{
			if(methodMap.get(request.getMethodName())==null){
				throw new RpcException("service has no exist method "+ JsonUtil.toJsonString(request) );
			}
			res.setResponse(methodMap.get(request.getMethodName()).invoke(target, request.getArgs()));
			return res;
		}catch(InvocationTargetException e ){
			res.setException(e.getCause());
		}catch(Throwable e){
			res.setException(new RpcException("service invoke error "+ ExceptionUtils.getStackTrace(e)));
		}
		return res;
	}
	
	public String getServiceName(){
		return ServicePathUtils.getServiceName(this.serviceInterface);
	}


	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public ServiceProfile getServiceProfile() {
		return serviceProfile;
	}

	public void setServiceProfile(ServiceProfile serviceProfile) {
		this.serviceProfile = serviceProfile;
	}

	public Map<String, Method> getMethodMap() {
		return methodMap;
	}

	
}
