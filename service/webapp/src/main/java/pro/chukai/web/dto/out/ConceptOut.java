package pro.chukai.web.dto.out;

import pro.chukai.web.dto.BaseDto;
import pro.chukai.web.model.Concept;
import pro.chukai.web.model.Field;
import pro.chukai.web.model.Concept;
import pro.chukai.web.model.Field;

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
