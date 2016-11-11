package com.chulung.search;

/**
 * Created by chulung on 2016/11/10.
 */
public interface Indexable {
    Integer getId();

    DocType getDocType();

    default String getDocId(){
        if (getDocType()==null || getId()==null) throw  new IllegalArgumentException("docType or id can't be null!");
        return getDocType().name()+"_"+getId();
    }

    default String getIdFromDocId(String docId){
        return docId.split("_")[1];
    }

}
