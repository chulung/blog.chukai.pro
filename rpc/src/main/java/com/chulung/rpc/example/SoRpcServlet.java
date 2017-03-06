package com.chulung.rpc.example;

import java.util.List;

import com.chulung.rpc.core.codec.impl.JavaCodec;
import com.chulung.rpc.server.AbstractRpcServlet;
import com.chulung.rpc.server.ServiceExporter;
import com.chulung.rpc.core.ServiceProfile;

public class SoRpcServlet extends AbstractRpcServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4584976078074654630L;
	
	public void registerServiceExporter(ServiceProfile sp, List<ServiceExporter> seList) throws Exception{
		ServiceExporter se = new ServiceExporter();
		se.setTarget(new SoServiceImpl());
		se.setServiceInterface(SoService.class);
		se.setServiceProfile(sp);
		se.init();
		seList.add(se);
	}
	
	public ServiceProfile  getServiceProfile(){
		ServiceProfile  sp = new ServiceProfile();
		sp.setServerContextPath("gos");
		sp.setServerPort("8080");
		sp.setServiceAppName("gos-query");
		sp.setServiceVersion("0.01");
		sp.setServiceUrlPrefix("rpc");
		sp.setRpcCodec(new JavaCodec());
		return sp;
	}

}
