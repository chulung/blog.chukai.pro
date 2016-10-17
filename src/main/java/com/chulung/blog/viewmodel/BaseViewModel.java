package com.chulung.blog.viewmodel;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.chulung.blog.model.BaseModel;

public class BaseViewModel {

	@SuppressWarnings("unchecked")
	public <V extends BaseViewModel, T extends BaseModel> V buildFrom(T model) {
		try {
			BeanUtils.copyProperties(this, model);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return (V) this;
	}
}
