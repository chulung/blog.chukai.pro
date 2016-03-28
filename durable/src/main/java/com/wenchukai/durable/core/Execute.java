package com.wenchukai.durable.core;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

public interface Execute {
	/**
	 * 连接线程共享
	 */
	static ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
	
	/**
	 * 按sql 查询 返回bean list
	 * @param sql
	 * @param params
	 * @param beanClass
	 * @return
	 */
	<T> List<T> executeQuery(String sql, Object[] params, Class<T> beanClass);
	
	/**
	 * 执行update
	 * @param sql
	 * @param params
	 * @return 受影响行数
	 */
	int executeUpdate(String sql, Object[] params);

	DataSource getDataSource();

	void setDataSource(DataSource dataSource);

	Connection getConnection() ;
	
	/**
	 * 执行insert
	 * @param sql
	 * @param params
	 * @return 主键
	 */
	Integer executeInsert(String sql, Object[] params);
}
