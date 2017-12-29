package com.wchukai.web.dto.out;

import com.wchukai.web.dto.BaseDto;
import com.wchukai.web.model.Concept;
import com.wchukai.web.model.Field;

import java.util.List;

/**
 * @author chukai
 */
public class ConceptOut extends Concept implements BaseDto<ConceptOut,Concept>{
    private List<Field> fields;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
