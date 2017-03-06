package com.chulung.rpc.server;

import java.io.IOException;

import com.chulung.common.util.JsonUtil;
import com.chulung.rpc.client.TestHelper;
import com.chulung.rpc.example.SoService;
import junit.framework.TestCase;

import org.apache.curator.test.TestingServer;

import static org.assertj.core.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chulung.rpc.core.RpcRequest;
import com.chulung.rpc.core.RpcResponse;
import com.chulung.rpc.example.So;
import com.chulung.rpc.example.SoServiceImpl;
import com.chulung.rpc.register.impl.ZkServiceRegister;
import com.chulung.rpc.utils.ZKClientUtils;

public class ServiceExporterTest extends TestCase {

    private TestingServer server;
    private ZkServiceRegister sr;

    private ServiceExporter exporter;

    @BeforeClass
    public void setUp() throws Exception {

        exporter = new ServiceExporter();
        server = new TestingServer();
        server.start();
        sr = new ZkServiceRegister();
        sr.setServiceProfile(TestHelper.getServiceProfile());
        sr.setZkConnStr(server.getConnectString());
        sr.init();

        exporter.setTarget(new SoServiceImpl());
        exporter.setServiceInterface(SoService.class);
        exporter.setServiceProfile(TestHelper.getServiceProfile());
        exporter.initMethodMap();
    }

    @AfterClass
    public void tearDown() throws IOException {
        server.stop();
        ZKClientUtils.clearZkClient();
    }

    @Test
    public void testInvoke() {
        RpcRequest request = new RpcRequest();
        request.setMethodName("getSoById");
        request.setArgs(new Object[]{2L});
        RpcResponse res = (RpcResponse) exporter.invoke(request);
        So so = (So) res.getResponse();
        assertThat(so.getId().equals(2L)).isTrue();

    }

    @Test
    public void testInvokeThrowException() {
        RpcRequest request = new RpcRequest();
        request.setMethodName("getSoException");
        request.setArgs(new Object[]{2L});
        RpcResponse res = exporter.invoke(request);
        assertThat(res.getException()).isInstanceOf(NullPointerException.class);
    }

}
