package com.chulung.craft.service.impl;

import com.chulung.ccache.annotation.CCache;
import com.chulung.craft.mapper.ColumnTypeMapper;
import com.chulung.craft.model.ColumnType;
import com.chulung.craft.service.ColumnTypeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ChuKai on 2016/11/6.
 */

@Service
public class ColumnTypeSeviceImpl implements ColumnTypeSevice {

    @Autowired
    private ColumnTypeMapper columnTypeMapper;


    @Override
    public List<ColumnType> getAllColumns(){
        return this.columnTypeMapper.selectAll();
    }

    @Override
    @CCache(liveSeconds = 1800)
    public Map<Integer,ColumnType> getIdColumnMap(){
        return this.columnTypeMapper.selectAll().stream().collect(Collectors.toMap(ColumnType::getId,c->c));
    }

    @Override
    @CCache(liveSeconds = 1800)
    public Map<String,ColumnType> getEnNameColumnMap(){
        return this.columnTypeMapper.selectAll().stream().collect(Collectors.toMap(ColumnType::getEnName,c->c));
    }
}
