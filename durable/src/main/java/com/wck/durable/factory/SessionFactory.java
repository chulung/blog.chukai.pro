package com.wck.durable.factory;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.wck.durable.core.Execute;
import com.wck.durable.core.Session;
import com.wck.durable.core.impl.ExecuteImpl;
import com.wck.durable.core.impl.SessionImpl;

public class SessionFactory {
	@Resource
	private DataSource dataSource;

	public Session openSession() {
		Execute execute = new ExecuteImpl();
		execute.setDataSource(dataSource);
		return new SessionImpl(execute);
	}


}
