package com.chulung.rpc.client;

import java.net.ConnectException;

import com.chulung.rpc.core.RpcException;
import com.chulung.rpc.core.RpcRequest;
import com.chulung.rpc.core.RpcResponse;
import com.chulung.rpc.example.So;

public class JdkRpcProxyFactoryRetryExtend extends JdkRpcProxyFactoryExtend {
	public static int reqCount = 0;
	
	public byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest){
		reqCount ++;
		if(reqCount==3){
			RpcResponse response = new RpcResponse();
			So so = So.createSo();
			Long id = (Long)rpcRequest.getArgs()[0];
			so.setId(id);
			response.setResponse(so);
			return this.getClientProfile().getRpcCodec().encode(response);
		}
		throw new RpcException(new ConnectException("can not connection server"));
	}

}
