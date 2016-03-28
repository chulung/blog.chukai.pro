package com.wenchukai.durable.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.wenchukai.durable.annotation.Ignore;
import com.wenchukai.durable.bean.Beaninfo;

public final class BeaninfoCfg {
	public static final BeaninfoCfg BEANINFO_CFG = new BeaninfoCfg();
	private Map<Class<?>, Beaninfo> beaninfoMap = new HashMap<Class<?>, Beaninfo>();

	private BeaninfoCfg() {

	}

	public Beaninfo getBeaninfo(Class<?> beanClass) {
		Beaninfo beaninfo = beaninfoMap.get(beanClass);
		if (beaninfo != null) {
			return beaninfo;
		}
		return createBeaninfo(beanClass);
	}

	public Beaninfo createBeaninfo(Class<?> beanClass) {
		Field[] fields = beanClass.getDeclaredFields();
		Map<String, Method> getMethodMap = new HashMap<String, Method>();
		Map<String, Method> setMethodMap = new HashMap<String, Method>();
		List<String> fieldNames = new ArrayList<String>();
		Table t = beanClass.getAnnotation(Table.class);
		String table = t != null ? t.name() : beanClass.getSimpleName();
		String primaryKey = null;
		boolean findPk = false;
		for (Field field : fields) {
			if (field.getAnnotation(Ignore.class) != null) {
				continue;
			}
			String fieldName = field.getName();
			if (!findPk) {
				if (field.getAnnotation(Id.class) != null) {
					primaryKey = field.getName();
					findPk = true;
				}
			}
			// 首字母大写
			String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method method;
			try {
				method = beanClass.getMethod("get" + methodName);
				getMethodMap.put(fieldName.toLowerCase(), method);
				Class<?> returnType = method.getReturnType();
				method = beanClass.getMethod("set" + methodName, returnType);
				setMethodMap.put(fieldName.toLowerCase(), method);
				fieldNames.add(fieldName.toLowerCase());
			} catch (NoSuchMethodException e) {
				continue;
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		if (!findPk) {
			throw new IllegalStateException("未找到id注解");
		}
		// 移除primaryKey 单独存储为字段
		fieldNames.remove(primaryKey);
		Beaninfo value = new Beaninfo(table, primaryKey, fieldNames, setMethodMap, getMethodMap);
		this.beaninfoMap.put(beanClass, value);
		return value;
	}
}