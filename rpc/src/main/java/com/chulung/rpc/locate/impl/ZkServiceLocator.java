package com.chulung.rpc.locate.impl;

import java.util.ArrayList;
import java.util.List;

import com.chulung.common.util.JsonUtil;
import com.chulung.rpc.core.ClientProfile;
import com.chulung.rpc.utils.ServicePathUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.chulung.rpc.core.RpcException;
import com.chulung.rpc.locate.ServiceLocator;
import com.chulung.rpc.utils.ZKClientUtils;

public class ZkServiceLocator extends ServiceLocator {
	
	private static final Logger logger = LoggerFactory.getLogger(ZkServiceLocator.class);
	
	private CuratorFramework zkClient;
	private String zkConnStr;
	
	public ZkServiceLocator(){}
	
	public ZkServiceLocator(String zkConnStr,ClientProfile clientProfile){
		this.setZkConnStr(zkConnStr);
		this.setClientProfile(clientProfile);
		init();
	}
	
	public void init() {
		zkClient = ZKClientUtils.getZKClient(zkConnStr);
		try {
			ZKClientUtils.ensure(zkClient, ServicePathUtils.getServicePath(this.getClientProfile()));
			initServiceUrlList();
		} catch (Exception e) {
			throw new RpcException("ServiceLocator init fail parentPath "+ServicePathUtils.getServicePath(this.getClientProfile()) ,e);
		}
	}
	
	public void initServiceUrlList() throws Exception{
		Watcher watcher = new Watcher(){
			public void process(WatchedEvent event) {
				logger.info("zk watcher receive event "+ event.toString());
				try{
					//processZKChildren(ZKClientHelper.getChildren(zkClient, parentPath));
					initServiceUrlList();
				}catch(Exception e){
					logger.error("watcher processZKChildren exception", e);
				}
			}
		};
		List<String> childrenKey = ZKClientUtils.watchedGetChildren(zkClient, 
				ServicePathUtils.getServicePath(ZkServiceLocator.this.getClientProfile()), watcher);
		if(logger.isInfoEnabled()){
			logger.info("initServiceUrlList get  childrenKey "+ JsonUtil.toJsonString(childrenKey));
		}
		processZKChildren(childrenKey);
	}
	
	public void processZKChildren(List<String> childrenKey)throws Exception{
		List<String> dataList = new ArrayList<String>();
		for(String child : childrenKey) {
			dataList.add(ZKClientUtils.getData(zkClient, ServicePathUtils.getServicePath(this.getClientProfile())+"/"+child));
		}
		updateServiceUrlList(dataList);
	}

	public String getZkConnStr() {
		return zkConnStr;
	}

	public void setZkConnStr(String zkConnStr) {
		this.zkConnStr = zkConnStr;
	}
	
}
