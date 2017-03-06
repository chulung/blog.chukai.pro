package com.chulung.rpc.core;

import com.chulung.rpc.core.codec.RpcCodec;

public class BaseProfile {
	
	private String serviceAppName;
	
	private String groupName = "default";
	
	private String serviceVersion;
	
	private RpcCodec rpcCodec;

	public String getServiceAppName() {
		return serviceAppName;
	}

	public void setServiceAppName(String serviceAppName) {
		this.serviceAppName = serviceAppName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public RpcCodec getRpcCodec() {
		return rpcCodec;
	}

	public void setRpcCodec(RpcCodec rpcCodec) {
		this.rpcCodec = rpcCodec;
	}
}
