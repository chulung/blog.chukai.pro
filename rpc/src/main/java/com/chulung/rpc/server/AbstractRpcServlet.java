package com.chulung.rpc.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chulung.rpc.register.ServiceRegister;
import com.chulung.rpc.utils.ServicePathUtils;
import org.apache.commons.io.IOUtils;

import com.caucho.services.server.ServiceContext;
import com.chulung.rpc.core.RpcException;
import com.chulung.rpc.core.RpcRequest;
import com.chulung.rpc.core.RpcResponse;
import com.chulung.rpc.core.ServiceProfile;
import com.chulung.rpc.register.impl.ZkServiceRegister;

public abstract class AbstractRpcServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Map<String,ServiceExporter> seMap = new HashMap<String,ServiceExporter>();
	
	public void init() throws ServletException {
		try{
			List<ServiceExporter> seList = new ArrayList<ServiceExporter>();
			registerServiceExporter(this.getServiceProfile(), seList);
			for( ServiceExporter se : seList){
				seMap.put(ServicePathUtils.getServiceName(se.getServiceInterface()), se);
			}
			ServiceRegister sr = new ZkServiceRegister();
			sr.setServiceProfile(this.getServiceProfile());
			sr.init();
			
		}catch(Exception e){
			throw new ServletException("servlet init exception",e);
		}
		
	}

	public void service(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (!req.getMethod().equals("POST")) {
			res.setStatus(500); // , "RPC Requires POST");
			PrintWriter out = res.getWriter();

			res.setContentType("text/html");
			out.println("<h1>RPC Requires POST</h1>");

			return;
		}

		String serviceId = req.getPathInfo().substring(1);
		ServletInputStream is = null;
		ServletOutputStream os = null;
		try {
			is = request.getInputStream();
			os = response.getOutputStream();

			response.setContentType("x-application/object");
			ServiceExporter se = getServiceExporter(serviceId);
			RpcResponse rpcResponse = null;
			if(se == null){
				rpcResponse = new RpcResponse();
				rpcResponse.setException(new RpcException("service "+ serviceId+" not found"));
			}else {
				byte[] input =IOUtils.toByteArray(is);
				RpcRequest rpcRequest = (RpcRequest)se.getServiceProfile().getRpcCodec().decode(input);
				rpcResponse = se.invoke(rpcRequest);
			}
			getServiceProfile().getRpcCodec().encode(rpcResponse, os);
		} catch (RuntimeException e) {
			throw e;
		}  catch (Throwable e) {
			throw new ServletException(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}
	
	public  ServiceExporter getServiceExporter(String serviceId){
		return seMap.get(serviceId);
	}
	
	public  abstract void registerServiceExporter(ServiceProfile sp,List<ServiceExporter> seList) throws Exception;
	
	public  abstract  ServiceProfile getServiceProfile();

}
