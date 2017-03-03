package com.chulung.website.service;

import com.chulung.website.model.ColumnType;

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
