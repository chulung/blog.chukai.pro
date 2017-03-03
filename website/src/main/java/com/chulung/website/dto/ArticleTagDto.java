package com.chulung.website.dto;

import com.chulung.website.model.ArticleTag;
import org.apache.commons.beanutils.PropertyUtils;

import javax.persistence.Transient;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wenchukai1 on 2017/3/3.
 */
public class ArticleTagDto extends ArticleTag {

    private Integer count;

    public ArticleTagDto(ArticleTag articleTag) {
        try {
            PropertyUtils.copyProperties(this, articleTag);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
