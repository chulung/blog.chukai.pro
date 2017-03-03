package com.chulung.metaweblog.xmlrpc;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class XmlRpcExecute {
	private XmlRpcClient client;

	public XmlRpcExecute() throws MalformedURLException {

		client = new XmlRpcClient();
		client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
	}

	public Object execute(String serverUrl, String pMethodName, Object[] pParams) throws XmlRpcException{
		try {
			XmlRpcClientConfigImpl config;
			config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(serverUrl));
			//设置UA，否则oschina接口会拒绝
			config.setUserAgent("Mozilla/4.0 (compatible; MSIE 9.11; Windows NT 6.1; Open Live Writer 1.0)");
			//不进行数据格式严格校验
			config.setEnabledForExtensions(true);
			client.setConfig(config);
			return client.execute(pMethodName, pParams);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} 
	}
}
