package com.chulung.metaclblog.struct;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class Struct<T extends Struct<T>> {
	/**
	 * 将Struct转化为MetaWeblog的Params map 默认直接反射所有字段名和值生成map 注意 字段类型只支持Struct
	 * String String数组，基本数据类型及其包装类
	 * 
	 * @return
	 */
	public Map<String, Object> toParams() {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				// 设置访问权限，直接反射获取字段
				field.setAccessible(true);
				Object fieldValue = field.get(this);
				// 如果字段为结构体，为null则设为空map，不为null则调用其toParams方法
				if (field.getType().getSuperclass() == Struct.class) {
					fieldValue = fieldValue == null ? new HashMap<>() : ((Struct<?>) fieldValue).toParams();
				} else if (field.getType().isArray()) {
					// 字符串数组处理
					fieldValue = fieldValue == null ? new String[] {} : (String[]) fieldValue;
				}

				map.put(field.getName(), fieldValue == null ? "" : fieldValue);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return map;
	}

	/**
	 * 远程调用返回参数转化为Struct，反射注入值
	 * 
	 * @param params
	 */
	public Struct(Map<String, Object> params) {
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				Object value = params.get(field.getName());
				if (value != null) {
					field.setAccessible(true);
					if (field.getType() == value.getClass()) {
						field.set(this, value);
					} else if (field.getType()==String.class) {
						field.set(this, value.toString());
					} else if (field.getType().getSuperclass() == Struct.class) {
						field.set(this, field.getType().getConstructor(Map.class).newInstance(value));
					}else{
						field.set(this, field.getType().getConstructor(value.getClass()).newInstance(value));
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Struct() {
	}

}
