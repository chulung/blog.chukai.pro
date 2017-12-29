package com.wchukai.web.service;

import com.wchukai.web.model.Field;

import java.util.Map;

/**
 * @author chukai
 */
public interface FieldService {
    Map<Integer, Field> selectFieldMap();
}
