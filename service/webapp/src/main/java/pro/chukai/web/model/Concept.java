package pro.chukai.web.model;

import pro.chukai.web.model.BaseModel;

/**
 * 概念
 *
 * @author chukai
 */
public class Concept extends BaseModel {

    private String conceptName;
    private String origin;
    /**
     * 概念的定义
     */
    private String definition;
    /**
     * 概念的解释
     */
    private String explanation;

    /**
     * 概念的应用
     */
    private String application;

    public String getConceptName() {
        return conceptName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}