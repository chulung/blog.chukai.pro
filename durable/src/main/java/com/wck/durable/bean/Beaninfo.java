package com.wck.durable.bean;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * bean信息.
 * 
 * @author CK
 * 
 */
public class Beaninfo {
	private String pk;
	private String table;
	private List<String> fieldNames;
	private Map<String, Method> setMethodMap;
	private Map<String, Method> getMethodMap;

	public Beaninfo() {
	}

	public Beaninfo(String table, String pk, List<String> fieldNames, Map<String, Method> setMethodMap,
			Map<String, Method> getMethodMap) {
		this.table = table;
		this.pk = pk;
		this.fieldNames = fieldNames;
		this.setMethodMap = setMethodMap;
		this.getMethodMap = getMethodMap;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public List<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public Map<String, Method> getSetMethodMap() {
		return setMethodMap;
	}

	public void setSetMethodMap(Map<String, Method> setMethodMap) {
		this.setMethodMap = setMethodMap;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Map<String, Method> getGetMethodMap() {
		return getMethodMap;
	}

	public Method getGetMethod(String fieldName) {
		return this.getMethodMap.get(fieldName);
	}

	public void setGetMethodMap(Map<String, Method> getMethodMap) {
		this.getMethodMap = getMethodMap;
	}

}
