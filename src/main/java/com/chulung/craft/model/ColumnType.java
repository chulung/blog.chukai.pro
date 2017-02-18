package com.chulung.craft.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;

/**
 * 网站文章栏目类型
 * Created by ChuKai on 2016/11/6.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColumnType extends  BaseModel{

    @Id
    private Integer id;
    private String cnName;
    private String enName;
    private String slogans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
