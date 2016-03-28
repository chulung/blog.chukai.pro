package com.wenchukai.durable.factory;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.wenchukai.durable.core.Execute;
import com.wenchukai.durable.core.Session;
import com.wenchukai.durable.core.impl.ExecuteImpl;
import com.wenchukai.durable.core.impl.SessionImpl;
import com.wenchukai.durable.logproxy.ExecuteLogProxy;

/**
 * 带日志功能的session，可打印sql 计算执行时间
 * @author chukai
 *
 */
public class SessionFactoryLogProxy {
	@Resource
	private DataSource dataSource;
	public Session openSession() {
		Execute execute = new ExecuteImpl();
		execute.setDataSource(dataSource);
		Execute newExecuteLogProxy = new ExecuteLogProxy().newExecuteLogProxy(execute);
		return new SessionImpl(newExecuteLogProxy);
	}

}
