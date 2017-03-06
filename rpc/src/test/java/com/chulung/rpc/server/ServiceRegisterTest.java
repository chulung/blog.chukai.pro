package com.chulung.rpc.server;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.curator.test.TestingServer;
import static org.assertj.core.api.Assertions.*;
import com.chulung.rpc.client.TestHelper;
import com.chulung.rpc.locate.impl.ZkServiceLocator;
import com.chulung.rpc.register.impl.ZkServiceRegister;
import com.chulung.rpc.utils.ZKClientUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class ServiceRegisterTest extends TestCase {
	
	private TestingServer server;
	private ZkServiceLocator sl;
	private ZkServiceRegister sg;
	
	@BeforeClass
	public void setUp() throws Exception{
		
		server = new TestingServer();
		server.start();
		//zkClient = ZKClientUtils.getZKClient(server.getConnectString());
		//zkClient = ZKClientHelper.getZKClient("192.168.59.103:2181");
		sl = new ZkServiceLocator(server.getConnectString(),TestHelper.getClientProfile());
		
		sg = new ZkServiceRegister();
		sg.setServiceProfile(TestHelper.getServiceProfile());
		sg.setZkConnStr(server.getConnectString());
		sg.init();
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		server.stop();
		ZKClientUtils.clearZkClient();
	}
	
	public void testRegister() throws Exception{
		System.out.println("testRegister start");
		sg.register();
		Thread.sleep(1000L);
		assertThat(sl.select()).isNotNull();
	}
	
	public void testUnRegister2() throws Exception{
		System.out.println("testUnRegister2 start");
		sg.register();
		Thread.sleep(1000L);
		sg.unRegister();
		Thread.sleep(1000L);
		assertThat(sl.select()).isNotNull();
		
		sg.register();
		Thread.sleep(1000L);
		assertThat(sl.select()).isNotNull();
	}
	
	

}
