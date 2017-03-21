package com.chulung.website.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;

/**
 * 网站文章栏目类型
 * Created by chulung on 2016/11/6.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColumnType extends  BaseModel{
    private String cnName;
    private String enName;
    private String slogans;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getSlogans() {
        return slogans;
    }

    public void setSlogans(String slogans) {
        this.slogans = slogans;
    }
}
