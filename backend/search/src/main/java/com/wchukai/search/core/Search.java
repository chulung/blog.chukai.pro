package com.wchukai.search.core;

import java.util.List;

/**
 * 创建索引及查询操作
 * Created by wchukai on 2016/11/14.
 */
public interface Search {

    /**
     * 创建索引 会根据id删除已有的
     *
     * @param doc 文档
     * @return
     */
    boolean createIndex(SearchDocument doc);

    /**
     * 批量创建索引 会根据id删除已有的
     *
     * @param docs
     * @return
     */
    boolean createIndex(List<SearchDocument> docs);

    /**
     * 清空所有索引
     */
    void clearAll();

    /**
     * @param queryStr
     * @param num      返回的最大条数
     * @return
     * @throws Exception
     */
    List<SearchDocument> search(String queryStr, int num) throws Exception;

    /**
     * 查询，默认最大10条
     *
     * @param key
     * @return
     * @throws Exception
     */
    List<SearchDocument> search(String key) throws Exception;
}
