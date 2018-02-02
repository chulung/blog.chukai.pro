package pro.chukai.web.dto;

import pro.chukai.web.model.BaseModel;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by chukai on 3/15/2017.
 */

public interface BaseDto<D extends BaseDto, M extends BaseModel> {

    default D buildFromModel(M model) {
        try {
            PropertyUtils.copyProperties(this, model);
            return (D) this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default M buildModel() {
        Class[] classes = this.getClass().getInterfaces();
        Type[] types = this.getClass().getGenericInterfaces();
        for (int i = 0; i < types.length; i++) {
            if (classes[i] == BaseDto.class) {
                Class<M> mClass = (Class<M>) ((ParameterizedType) types[i]).getActualTypeArguments()[1];
                try {
                    M m = mClass.newInstance();
                    PropertyUtils.copyProperties(m, this);
                    return m;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException(BaseDto.class + " is not implemented. ");
    }
}
