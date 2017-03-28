package com.chulung.website.service;

import com.chulung.website.model.Column;

import java.util.List;
import java.util.Map;

/**
 * Created by chulung on 2016/11/6.
 */
public interface ColumnSevice {

    List<Column> getAllColumns();

    Map<Integer,Column> getIdColumnMap();

    Map<String,Column> getEnNameColumnMap();
}
