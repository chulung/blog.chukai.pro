package pro.chukai.metaweblog.util;

import pro.chukai.metaweblog.struct.Post;
import pro.chukai.metaweblog.struct.Struct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射进行数据转化工具
 * Created by chukai on 2017/2/24.
 */
public class ReflectUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class);

    public static <T> T reflectFields(T obj, Map<String, Object> valueMap) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object value = valueMap.get(field.getName());
            try {
                if (value != null) {
                    field.setAccessible(true);
                    if (field.getType() == value.getClass()) {
                        field.set(obj, value);
                    } else if (field.getType() == String.class) {
                        field.set(obj, value.toString());
                    } else if (field.getType().getSuperclass() == Struct.class) {
                        Object struct = field.getType().getConstructor().newInstance();
                        reflectFields(struct, (Map<String, Object>) value);
                        field.set(obj, struct);
                    } else if (value.getClass() == Object[].class) {
                        Object[] arr = (Object[]) value;
                        String[] ss = new String[arr.length];
                        for (int i = 0; i < arr.length; i++) {
                            ss[i] = arr[i].toString();
                        }
                        field.set(obj, ss);
                    } else if (field.getType() == Date.class && value.getClass() == Long.class) {
                        field.set(obj, new Date((long) value));
                    } else {
                        field.set(obj, field.getType().getConstructor(value.getClass()).newInstance(value));
                    }
                }
            } catch (Exception e) {
                LOGGER.error("\nfield={}\nvalue={}\nvalueType={}\nerror={}", new Object[]{field, value, value.getClass(), e});
            }
        }
        return obj;
    }


    /**
     * 将Struct转化为MetaWeblog的Params map 默认直接反射所有字段名和值生成map 注意 字段类型只支持Struct
     * String String数组，基本数据类型及其包装类
     *
     * @return map
     */
    public static Map<String, Object> toXMLRPCParams(Struct<Post> struct) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = struct.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                // &#x8bbe;&#x7f6e;&#x8bbf;&#x95ee;&#x6743;&#x9650;&#xff0c;&#x76f4;&#x63a5;&#x53cd;&#x5c04;&#x83b7;&#x53d6;&#x5b57;&#x6bb5;
                field.setAccessible(true);
                Object fieldValue = field.get(struct);
                // 如果字段为结构体，为null则设为空map，不为null则调用其toParams方法
                if (field.getType().getSuperclass() == Struct.class) {
                    fieldValue = fieldValue == null ? new HashMap<>() : toXMLRPCParams((Struct<Post>) fieldValue);
                } else if (field.getType().isArray()) {
                    // 字符串数组处理
                    fieldValue = fieldValue == null ? new String[]{} : fieldValue;
                }

                map.put(field.getName(), fieldValue == null ? "" : fieldValue);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return map;
    }
}
