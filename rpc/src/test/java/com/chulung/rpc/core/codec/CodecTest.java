package com.chulung.rpc.core.codec;

import java.util.Date;
import java.util.UUID;

import com.chulung.rpc.core.codec.impl.JavaCodec;
import com.chulung.rpc.example.So;
import com.chulung.rpc.core.RpcRequest;

public class CodecTest {

	public static void main(String[] args) {
		
		RpcRequest request = new RpcRequest();
		request.setServiceFullName("userService");
		request.setRequestId(UUID.randomUUID().toString());
		request.setRequestTime(new Date());
		request.setMethodName("getUserById");
		request.setClientAppName("3rd/b2b2c");
		request.setArgs(new Object[]{So.createSo()});
		System.out.println("RpcRequest with so args test");
		count(10,request);
		count(100000,request);
	}
	
	private static void count(int count,RpcRequest request){
		java(count,request);
	}
	
	private static void java(int count,RpcRequest request) {
		try{
			long start = System.currentTimeMillis();
			JavaCodec javaCodec = new JavaCodec();
			int length = 0;
			for(int i=0;i<count;i++){
				length = javaCodec.encode(request).length ;
			}
			System.out.println("javaCodec cost "+(System.currentTimeMillis() - start)+" ,byte length "+length+ " ,count "+count);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
