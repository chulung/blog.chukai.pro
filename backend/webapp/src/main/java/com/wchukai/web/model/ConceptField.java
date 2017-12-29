package com.wchukai.web.model;

import com.wchukai.web.model.BaseModel;

/**
 * @author chukai
 */
public class ConceptField extends BaseModel{
    private Integer conceptId;
    private Integer fieldId;

    public Integer getConceptId() {
        return conceptId;
    }

    public void setConceptId(Integer conceptId) {
        this.conceptId = conceptId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }
}
