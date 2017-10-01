package com.wchukai.web.service;

import com.wchukai.web.model.Column;

import java.util.List;
import java.util.Map;

/**
 * Created by wchukai on 2016/11/6.
 */
public interface ColumnSevice {

    List<Column> getAllColumns();

    Map<Integer, Column> getIdColumnMap();

    Map<String, Column> getEnNameColumnMap();
}
