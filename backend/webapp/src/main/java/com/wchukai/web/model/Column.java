package com.wchukai.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Table;

/**
 * 网站文章栏目类型
 * Created by wchukai on 2016/11/6.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "columns")
public class Column extends BaseModel {

    private static final long serialVersionUID = -7378879291119999310L;
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
