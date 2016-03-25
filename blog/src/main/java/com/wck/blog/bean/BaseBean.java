package com.wck.blog.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import com.wck.util.DateUtil;

public class BaseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6831595989095431471L;

	public String toString() {
		StringBuffer propBuffer = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		propBuffer.append("[").append(this.getClass()).append("] | ");

		for (Field field : fields) {
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			if (isStatic) {
				continue;
			}
			String fieldName = field.getName();
			String getterMethod = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
			try {
				Method getMethod = this.getClass().getMethod(getterMethod, (Class[]) null);
				Object fieldValue = getMethod.invoke(this, (Object[]) null);
				if (fieldValue instanceof Date) {
					fieldValue = DateUtil.parseDate((Date) fieldValue);
				}
				propBuffer.append(fieldName).append(":").append(fieldValue).append(", ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return propBuffer.substring(0, propBuffer.length() - 2);
	}
}
