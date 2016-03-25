package com.wck.durable.factory;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.wck.durable.core.Execute;
import com.wck.durable.core.Session;
import com.wck.durable.core.impl.ExecuteImpl;
import com.wck.durable.core.impl.SessionImpl;
import com.wck.durable.logproxy.ExecuteLogProxy;

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
