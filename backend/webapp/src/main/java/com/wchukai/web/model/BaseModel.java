package com.wchukai.web.model;

import com.wchukai.common.util.DateUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public class BaseModel implements Serializable {

    private static final long serialVersionUID = -6831595989095431471L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
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
                if (fieldValue != null && fieldValue instanceof Date) {
                    fieldValue = DateUtils.format((Date) fieldValue);
                }
                propBuffer.append(fieldName).append(":").append(fieldValue).append(", ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return propBuffer.substring(0, propBuffer.length() - 2);
    }
}
