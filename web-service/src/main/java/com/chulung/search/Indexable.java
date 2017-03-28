package com.chulung.search;

/**
 * 索引实现接口
 * Created by chulung on 2016/11/10.
 */
public interface Indexable {
    Integer getId();

    void setId(Integer id);

    DocType docType();

    default String docId(){
        if (docType()==null || getId()==null) throw  new IllegalArgumentException("docType or id can't be null!");
        return docType().name()+"_"+getId();
    }

    default void setIdFromDocId(String docId){
         this.setId(Integer.valueOf(docId.split("_")[1]));
    }

}
