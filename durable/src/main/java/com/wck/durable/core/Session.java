package com.wck.durable.core;

import java.util.List;

import com.wck.durable.bean.Queryer;

/**
 * 查询会话接口.
 * 
 * @author CK
 * 
 */
public interface Session {

	<T> Integer insert(T bean);

	<T> boolean update(T bean);

	<T> boolean delete(T bean);

	int execute(String sql, Object... params);

	<T> List<T> queryList(T bean);

	<T> T queryOne(T bean);

	<T> T queryOne(Queryer<T> queryer);

	<T> T queryOne(String sql, Class<T> beanClass, Object... params);

	<T> List<T> queryList(Queryer<T> queryer);

	<T> List<T> queryList(String sql, Class<T> beanClass, Object... params);

	<T> int count(Queryer<T> queryer);
}
