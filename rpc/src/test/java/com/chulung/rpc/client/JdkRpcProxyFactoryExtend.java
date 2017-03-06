package com.chulung.rpc.client;

import com.chulung.rpc.core.RpcRequest;
import com.chulung.rpc.core.RpcResponse;
import com.chulung.rpc.example.So;

public class JdkRpcProxyFactoryExtend extends JdkRpcProxyFactoryBean {
	
	public byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest){
		RpcResponse response = new RpcResponse();
		So so = So.createSo();
		Long id = (Long)rpcRequest.getArgs()[0];
		so.setId(id);
		response.setResponse(so);
		return this.getClientProfile().getRpcCodec().encode(response);
		
	}

}
