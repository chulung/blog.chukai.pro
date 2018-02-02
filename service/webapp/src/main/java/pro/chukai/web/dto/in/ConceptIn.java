package pro.chukai.web.dto.in;

import pro.chukai.web.model.Concept;
import pro.chukai.web.model.Concept;

/**
 * @author chukai
 */
public class ConceptIn extends Concept {
    private Integer feildId;

    private int page;

    /**
     * 随机查询
     */
    private boolean random;

    public Integer getFeildId() {
        return feildId;
    }

    public void setFeildId(Integer feildId) {
        this.feildId = feildId;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
