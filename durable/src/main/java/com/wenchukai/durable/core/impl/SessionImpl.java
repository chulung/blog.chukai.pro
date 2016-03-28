package com.wenchukai.durable.core.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import com.wenchukai.durable.bean.Beaninfo;
import com.wenchukai.durable.bean.Queryer;
import com.wenchukai.durable.config.BeaninfoCfg;
import com.wenchukai.durable.core.Execute;
import com.wenchukai.durable.core.Session;
import com.wenchukai.durable.throwable.MultiResultError;

public class SessionImpl implements Session {
	protected Execute execute;
	private static final int GET_OR_SET_LENGTH = 3;

	public SessionImpl(Execute execute) {
		this.execute = execute;
	}

	protected String getFiledName(Method method) {
		return method.getName().substring(GET_OR_SET_LENGTH).toLowerCase();
	}

	public <T> boolean delete(T bean) {
		Beaninfo beaninfo = BeaninfoCfg.BEANINFO_CFG.getBeaninfo(bean.getClass());
		String sql = "delete from " + beaninfo.getTable();
		List<Object> params = new ArrayList<Object>();
		sql += getCondition(bean, params, beaninfo.getGetMethodMap());
		return execute.executeUpdate(sql, params.toArray()) == 1;
	}

	public <T> Integer insert(T bean) {
		Beaninfo beaninfo = BeaninfoCfg.BEANINFO_CFG.getBeaninfo(bean.getClass());
		StringBuffer sql = new StringBuffer("insert into " + beaninfo.getTable() + "(");
		StringBuffer sb = new StringBuffer(" values(");
		List<Object> params = new ArrayList<Object>();
		// 迭代bean的字段，拼接sql语句
		beaninfo.getFieldNames().forEach(field -> {
			Method method = beaninfo.getGetMethod(field);
			try {
				if (method == null) {
					throw new IllegalArgumentException(field + ":getMethodNotFound");
				}
				Object obj = method.invoke(bean);
				if (obj!=null) {
					sql.append(field).append(",");
					params.add(obj);
					sb.append("?,");
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		});

		sql.replace(sql.length() - 1, sql.length(), "");
		sql.append(")");
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append(")");
		sql.append(sb);
		return  execute.executeInsert(sql.toString(), params.toArray());
	}

	public <T> boolean update(T bean) {
		Beaninfo beaninfo = BeaninfoCfg.BEANINFO_CFG.getBeaninfo(bean.getClass());
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("update " + beaninfo.getTable() + " set ");
		StringBuffer update = new StringBuffer();
		beaninfo.getFieldNames().forEach(field -> {
			Method method = beaninfo.getGetMethod(field);
			try {
				if (method == null) {
					throw new IllegalArgumentException(field + ":getMethodNotFound");
				}
				Object val = method.invoke(bean);
				if (val != null) {
					update.append(field + "=?,");
					params.add(val);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		sql.append(update.substring(0, update.length() - 1));
		sql.append(" where " + beaninfo.getPk() + "=?");
		try {
			params.add(beaninfo.getGetMethod(beaninfo.getPk()).invoke(bean));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return execute.executeUpdate(sql.toString(), params.toArray()) == 1;
	}

	private String getCondition(Object bean, List<Object> params, Map<String, Method> map) {
		final StringBuffer whereCondition = new StringBuffer(" where 1=1 ");
		map.forEach((fieldName, method) -> {
			try {
				Object val = method.invoke(bean);
				if (val != null) {
					whereCondition.append("and " + fieldName + "=? ");
					params.add(val);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return whereCondition.toString();
	}

	@Override
	public <T> List<T> queryList(T bean) {
		return this.queryList(Queryer.of(bean));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryList(Queryer<T> queryer) {
		T bean = queryer.getBean();
		Beaninfo beaninfo = BeaninfoCfg.BEANINFO_CFG.getBeaninfo(bean.getClass());
		StringBuffer sql = new StringBuffer("select * from " + beaninfo.getTable());
		List<Object> params = new ArrayList<Object>();
		sql.append(getCondition(bean, params, beaninfo.getGetMethodMap()));
		sql.append(queryer.getOrderByString()).append(queryer.pagingString());
		return execute.executeQuery(sql.toString(), params.toArray(), (Class<T>) bean.getClass());

	}

	@Override
	public <T> int count(Queryer<T> queryer) {
		T bean = queryer.getBean();
		Beaninfo beaninfo = BeaninfoCfg.BEANINFO_CFG.getBeaninfo(bean.getClass());
		StringBuffer sql = new StringBuffer("select count(*) count from " + beaninfo.getTable());
		List<Object> params = new ArrayList<Object>();
		sql.append(getCondition(bean, params, beaninfo.getGetMethodMap())).append(queryer.getOrderByString())
				.append(queryer.pagingString());
		List<Count> countList = execute.executeQuery(sql.toString(), params.toArray(), Count.class);
		return countList.isEmpty() ? 0 : countList.get(0).getCount();
	}

	@Override
	public <T> List<T> queryList(String sql, Class<T> beanClass, Object... params) {
		return execute.executeQuery(sql, params, beanClass);
	}

	@Override
	public <T> T queryOne(String sql, Class<T> beanClass, Object... params) {
		List<T> list = this.queryList(sql, beanClass, params);
		if (list.size() > 1) {
			throw new MultiResultError("find " + list.size() + "results");
		}
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public int execute(String sql, Object... params) {
		return execute.executeUpdate(sql, params);
	}

	@Override
	public <T> T queryOne(T bean) {
		return this.queryOne(Queryer.of(bean));
	}

	@Override
	public <T> T queryOne(Queryer<T> queryer) {
		List<T> list = this.queryList(queryer);
		if (list.size() > 1) {
			throw new MultiResultError("find " + list.size() + "results");
		}
		return list.isEmpty() ? null : list.get(0);
	}

	static final class Count {
		@Id
		private int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}

}
