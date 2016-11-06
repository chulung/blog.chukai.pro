package com.chulung.craft.service;

import com.chulung.craft.model.ColumnType;

import java.util.List;
import java.util.Map;

/**
 * Created by ChuKai on 2016/11/6.
 */
public interface ColumnTypeSevice {

    List<ColumnType> getAllColumns();

    Map<Integer,ColumnType> getIdColumnMap();

    Map<String,ColumnType> getEnNameColumnMap();
}
