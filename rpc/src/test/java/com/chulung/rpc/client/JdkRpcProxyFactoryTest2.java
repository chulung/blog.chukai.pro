package com.chulung.rpc.client;

import com.chulung.common.util.JsonUtil;
import com.chulung.rpc.core.ClientProfile;
import com.chulung.rpc.core.codec.impl.JavaCodec;
import com.chulung.rpc.example.So;
import com.chulung.rpc.example.SoService;
import com.chulung.rpc.locate.ServiceLocator;
import com.chulung.rpc.locate.impl.ZkServiceLocator;

public class JdkRpcProxyFactoryTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClientProfile cp = new ClientProfile();
		cp.setClientAppName("test");
		cp.setServiceAppName("gos-query");
		cp.setServiceVersion("0.01");
		cp.setRpcCodec(new JavaCodec());
		cp.setReadTimeout(30000);
		ServiceLocator sl = new ZkServiceLocator();
		sl.setClientProfile(TestHelper.getClientProfile());
		SoService service =
				(SoService)JdkRpcProxyFactoryBean.create(SoService.class, sl);
		So so = service.getSoById(2L);
		System.out.println(JsonUtil.toJsonString(so));
		
	}

}
