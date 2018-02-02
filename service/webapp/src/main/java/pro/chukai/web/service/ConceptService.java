package pro.chukai.web.service;

import pro.chukai.web.dto.Result;
import pro.chukai.web.dto.in.ConceptIn;
import pro.chukai.web.dto.out.ConceptOut;
import pro.chukai.web.dto.Result;

import java.util.List;

/**
 * @author chukai
 */
public interface ConceptService {

   Result selectConcept(ConceptIn conceptIn);
    Result selectConcepts(ConceptIn conceptIn);

    Result liveSearch(String key);

    Result selectFields();
}
