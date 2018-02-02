package pro.chukai.web.model;

import pro.chukai.web.model.BaseModel;

/**
 * @author chukai
 */
public class Field extends BaseModel {
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
