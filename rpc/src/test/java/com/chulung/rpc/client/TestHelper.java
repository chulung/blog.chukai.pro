package com.chulung.rpc.client;

import com.chulung.rpc.core.ClientProfile;
import com.chulung.rpc.core.ServiceProfile;
import com.chulung.rpc.core.codec.impl.JavaCodec;

public class TestHelper {
	
	public static ClientProfile getClientProfile(){
		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setServiceAppName("user");
		clientProfile.setServiceVersion("0.01");
		clientProfile.setRpcCodec(new JavaCodec());
		clientProfile.setClientAppName("test");
		return clientProfile;
	}
	
	public static ServiceProfile getServiceProfile(){
		ServiceProfile  sp = new ServiceProfile();
		sp.setServiceAppName("user");
		sp.setServiceVersion("0.01");
		sp.setRpcCodec(new JavaCodec());
		sp.setServerContextPath("user");
		sp.setServerPort("8080");
		sp.setServiceUrlPrefix("rpc");
		return sp;
	}

}
