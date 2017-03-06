package com.chulung.rpc.core.requestHandler;

import com.chulung.rpc.core.ClientProfile;
import com.chulung.rpc.core.RpcRequest;

public interface RequestHandler {
	
	public byte[] doRequest(RpcRequest rpcRequest, byte[] byteRequest, ClientProfile clientProfile, String serviceUrl);

}
