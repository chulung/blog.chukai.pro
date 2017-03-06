package com.chulung.rpc.utils;

import com.chulung.rpc.core.BaseProfile;
import com.chulung.rpc.core.ServiceProfile;

public class ServicePathUtils {
	
	public static  String getServicePath(BaseProfile baseProfile)  {
		StringBuilder sb = new StringBuilder(50);
		sb.append("/").append(baseProfile.getServiceAppName())
		.append("/").append(baseProfile.getGroupName())
		.append("/").append(baseProfile.getServiceVersion());
		return sb.toString();
	}
	
	public static String  getServerGroupName(ServiceProfile serviceProfile){
		StringBuilder sb = new StringBuilder(50);
		sb.append("/").append(serviceProfile.getServiceAppName())
		.append("/").append("group")
		.append("/").append(IpUtils.getLocalIp())
		.append(":").append(serviceProfile.getServerPort());
		return sb.toString();
	}
	
	public static String getServiceName(Class<?> serviceInterface){
		return getServiceName(serviceInterface.getName());
	}
	
	public static String getServiceName(String serviceFullName) {
		int index = serviceFullName.lastIndexOf(".");
		String simpleServiceName = null;
		if(index==-1) {
			simpleServiceName =  serviceFullName.toLowerCase();
		}else {
			simpleServiceName =  serviceFullName.substring(index+1).toLowerCase();
		}
		return simpleServiceName;
	}

}
