package com.chulung.rpc.core.codec;

import java.io.InputStream;
import java.io.OutputStream;

import com.chulung.rpc.core.RpcException;


public interface RpcCodec {
	
	public byte[] encode(Object request) throws RpcException;
	
	public Object decode(byte[] input) throws RpcException ;
	
	public void encode(Object request,OutputStream outPut) throws RpcException;
	
	public Object decode(InputStream is) throws RpcException;

}
