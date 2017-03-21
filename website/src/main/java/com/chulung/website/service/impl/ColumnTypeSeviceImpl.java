package com.chulung.website.service.impl;

import com.chulung.website.mapper.ColumnTypeMapper;
import com.chulung.website.model.ColumnType;
import com.chulung.website.service.ColumnTypeSevice;
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
public class ColumnTypeSeviceImpl implements ColumnTypeSevice {

    @Autowired
    private ColumnTypeMapper columnTypeMapper;


    @Override
    public List<ColumnType> getAllColumns(){
        return this.columnTypeMapper.selectAll();
    }

    @Override
   @Cacheable
    public Map<Integer,ColumnType> getIdColumnMap(){
        return this.columnTypeMapper.selectAll().stream().collect(Collectors.toMap(ColumnType::getId,c->c));
    }

    @Override
   @Cacheable
    public Map<String,ColumnType> getEnNameColumnMap(){
        return this.columnTypeMapper.selectAll().stream().collect(Collectors.toMap(ColumnType::getEnName,c->c));
    }
}
