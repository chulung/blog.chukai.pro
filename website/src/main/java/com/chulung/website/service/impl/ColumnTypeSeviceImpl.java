package com.chulung.website.service.impl;

import com.chulung.website.mapper.ColumnMapper;
import com.chulung.website.model.Column;
import com.chulung.website.service.ColumnSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chulung on 2016/11/6.
 */

@Service
@CacheConfig(cacheNames = "forever")
public class ColumnTypeSeviceImpl implements ColumnSevice {

    @Autowired
    private ColumnMapper columnTypeMapper;


    @Override
    public List<Column> getAllColumns() {
        return this.columnTypeMapper.selectAll();
    }

    @Override
    @Cacheable(cacheNames = "halfhour",key = "'idColumnMap'")
    public Map<Integer, Column> getIdColumnMap() {
        List<Column> columns = this.columnTypeMapper.selectAll();
        return columns.stream().collect(
                Collectors.toMap(Column::getId, c -> c)
        );
    }

    @Override
    @Cacheable(cacheNames = "halfhour",key = "'enNameColumnMap'")
    public Map<String, Column> getEnNameColumnMap() {
        return this.columnTypeMapper.selectAll().stream().collect(Collectors.toMap(Column::getEnName, c -> c));
    }
}
