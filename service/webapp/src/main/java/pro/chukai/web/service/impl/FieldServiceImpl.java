package pro.chukai.web.service.impl;

import pro.chukai.web.mapper.FieldMapper;
import pro.chukai.web.model.Field;
import pro.chukai.web.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pro.chukai.web.model.Field;
import pro.chukai.web.service.FieldService;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chukai
 */
@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldMapper fieldMapper;

    @Cacheable(cacheNames = "halfhour", key = "'selectFieldMap'")
    @Override
    public Map<Integer, Field> selectFieldMap() {
       return this.fieldMapper.selectAll().stream().collect(Collectors.toMap(Field::getId, c -> c));
    }
}
