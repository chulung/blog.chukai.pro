package com.chulung.common.util;

import org.assertj.core.util.Maps;

import java.util.Map;

/**
 * Created by chulung on 2017/3/9.
 */
public class TestBean {
    private String string;
    private Integer integer;
    private Long aLong;
    private Map<Object,Object> map;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public static TestBean getTestBean(){
        TestBean testBean=new TestBean();
        testBean.setaLong(111l);
        testBean.setInteger(111);
        testBean.setString("asdasd");
        testBean.setMap(Maps.newHashMap("a",1));
        return  testBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestBean testBean = (TestBean) o;

        if (string != null ? !string.equals(testBean.string) : testBean.string != null) return false;
        if (integer != null ? !integer.equals(testBean.integer) : testBean.integer != null) return false;
        if (aLong != null ? !aLong.equals(testBean.aLong) : testBean.aLong != null) return false;
        return map != null ? map.equals(testBean.map) : testBean.map == null;
    }

    @Override
    public int hashCode() {
        int result = string != null ? string.hashCode() : 0;
        result = 31 * result + (integer != null ? integer.hashCode() : 0);
        result = 31 * result + (aLong != null ? aLong.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
    }
}
