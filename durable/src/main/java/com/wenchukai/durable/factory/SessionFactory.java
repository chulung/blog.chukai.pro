package com.wenchukai.durable.factory;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.wenchukai.durable.core.Execute;
import com.wenchukai.durable.core.Session;
import com.wenchukai.durable.core.impl.ExecuteImpl;
import com.wenchukai.durable.core.impl.SessionImpl;

public class SessionFactory {
	@Resource
	private DataSource dataSource;

	public Session openSession() {
		Execute execute = new ExecuteImpl();
		execute.setDataSource(dataSource);
		return new SessionImpl(execute);
	}


}
