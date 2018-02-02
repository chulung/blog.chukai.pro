package pro.chukai.web.service.impl;

import com.github.pagehelper.PageHelper;
import pro.chukai.search.ConceptSearchHandler;
import pro.chukai.web.dto.Result;
import pro.chukai.web.dto.in.ConceptIn;
import pro.chukai.web.dto.out.ConceptOut;
import pro.chukai.web.enumerate.ResultCodeEnum;
import pro.chukai.web.mapper.ConceptFieldMapper;
import pro.chukai.web.mapper.ConceptMapper;
import pro.chukai.web.model.Concept;
import pro.chukai.web.model.ConceptField;
import pro.chukai.web.model.Field;
import pro.chukai.web.service.ConceptService;
import pro.chukai.web.service.FieldService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.chukai.web.dto.Result;
import pro.chukai.web.dto.in.ConceptIn;
import pro.chukai.web.dto.out.ConceptOut;
import pro.chukai.web.enumerate.ResultCodeEnum;
import pro.chukai.web.mapper.ConceptMapper;
import pro.chukai.web.model.Concept;
import pro.chukai.web.model.Field;
import pro.chukai.web.service.ConceptService;
import pro.chukai.web.service.FieldService;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chukai
 */
@Service
public class ConceptServiceImpl implements ConceptService {

    @Autowired
    private ConceptMapper conceptMapper;

    @Autowired
    private ConceptFieldMapper conceptFieldMapper;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private ConceptSearchHandler conceptSearchHandler;

    @Override
    public Result selectConcept(ConceptIn conceptIn) {
        if (StringUtils.isBlank(conceptIn.getConceptName())) {
            return Result.of(ResultCodeEnum.PARAM_ERROR);
        }

        Concept concept = new Concept();
        concept.setConceptName(conceptIn.getConceptName());
        concept = conceptMapper.selectOne(concept);
        if (concept == null) {
            return Result.success(null);
        }
        ConceptOut conceptOut = new ConceptOut().buildFromModel(concept);
        ConceptField field = new ConceptField();
        field.setConceptId(concept.getId());
        Map<Integer, Field> map = fieldService.selectFieldMap();
        conceptOut.setFields(conceptFieldMapper.select(field).stream().map(f -> {
            return map.get(f.getFieldId());
        }).collect(Collectors.toList()));
        return Result.success(conceptOut);
    }

    @Override
    public Result selectConcepts(ConceptIn conceptIn) {
        if (conceptIn.isRandom()) {
            return Result.success(conceptSearchHandler.randomSelect(10));
        }
        PageHelper.startPage(conceptIn.getPage(),10);
        return Result.success(conceptMapper.selectAll());
    }

    @Override
    public Result liveSearch(String key) {
        if (key == null || !key.matches("^[a-zA-Z0-9\\u4E00-\\u9FA5]+$")) {
            return Result.of(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.success(conceptSearchHandler.liveSearch(key));
    }

    @Override
    public Result selectFields() {
        return Result.success(fieldService.selectFieldMap().values());
    }
}
