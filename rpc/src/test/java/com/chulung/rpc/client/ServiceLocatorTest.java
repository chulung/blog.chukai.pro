package com.chulung.rpc.client;

import java.io.IOException;

import com.chulung.rpc.locate.impl.ZkServiceLocator;
import com.chulung.rpc.utils.ServicePathUtils;
import junit.framework.TestCase;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.chulung.rpc.utils.ZKClientUtils;

public class ServiceLocatorTest extends TestCase {
	
	private TestingServer server;
	private CuratorFramework zkClient;
	private ZkServiceLocator sl;
	
	@BeforeClass
	public void setUp() throws Exception{
		server = new TestingServer();
		server.start();
		zkClient = ZKClientUtils.getZKClient(server.getConnectString());
		sl = new ZkServiceLocator(server.getConnectString(),TestHelper.getClientProfile());
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		server.stop();
		ZKClientUtils.clearZkClient();
	}
	
	public void testInitServiceUrlList() throws Exception{
		ZKClientUtils.createEphemeral(zkClient, ServicePathUtils.getServicePath(sl.getClientProfile())+"/" + "dq", "dengqiao".getBytes());
		Thread.sleep(1000);
		assertEquals(1, sl.getServiceUrlMap().size());
		assertEquals("dengqiao", sl.getServiceUrlMap().get(1));
	}
	

}
