package com.chulung.blog.service.impl;

import com.chulung.blog.enumerate.DictionaryTypeEnum;
import com.chulung.blog.mapper.DictionaryMapper;
import com.chulung.blog.model.Dictionary;
import com.chulung.blog.service.DictionaryService;
import com.chulung.ccache.annotation.CCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ChuKai on 2016/10/30.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public List<Dictionary> getDictListByType(DictionaryTypeEnum dictionaryTypeEnum) {
        Dictionary record=new Dictionary();
        record.setDictType(DictionaryTypeEnum.ARTICLE_TYPE);
        return this.dictionaryMapper.select(record);
    }
    @Override
    @CCache(liveSeconds = 3600)
    public Map<String,String> getDictValueMap(DictionaryTypeEnum dictionaryTypeEnum) {
        return this.getDictListByType(dictionaryTypeEnum).parallelStream().collect(Collectors.toMap(Dictionary::getDictValue,Dictionary::getDictDesc));
    }

}
