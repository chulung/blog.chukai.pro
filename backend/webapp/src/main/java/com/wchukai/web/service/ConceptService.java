package com.wchukai.web.service;

import com.wchukai.web.dto.Result;
import com.wchukai.web.dto.in.ConceptIn;
import com.wchukai.web.dto.out.ConceptOut;

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
