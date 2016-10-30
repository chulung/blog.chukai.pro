package com.chulung.blog.service;

import com.chulung.blog.enumerate.DictionaryTypeEnum;
import com.chulung.blog.model.Dictionary;
import com.chulung.ccache.annotation.CCache;

import java.util.List;
import java.util.Map;

/**
 * Created by ChuKai on 2016/10/30.
 */
public interface DictionaryService {
    List<Dictionary> getDictListByType(DictionaryTypeEnum dictionaryTypeEnum);

    @CCache(liveSeconds = 3600)
    Map<String,String> getDictValueMap(DictionaryTypeEnum dictionaryTypeEnum);
}
