package com.chulung.metaclblog.xmlrpc;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.springframework.stereotype.Component;

@Component
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
			client.setConfig(config);
			return client.execute(pMethodName, pParams);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} 
	}
}
