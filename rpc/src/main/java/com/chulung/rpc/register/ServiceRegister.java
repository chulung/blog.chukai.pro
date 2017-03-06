package com.chulung.rpc.register;

import com.chulung.rpc.core.ServiceProfile;

public  interface ServiceRegister {
	
	public void init();
	
	public void register() ;
	
	public void unRegister();
	
	public void setServiceProfile(ServiceProfile serviceProfile);

}
