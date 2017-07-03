package com.chulung.website.dto.out;

import com.chulung.website.dto.BaseDto;
import com.chulung.website.model.ArticleDraft;

/**
 * Created by chulung on 2017/3/28.
 */
public class ArticleDraftOut extends ArticleDraft implements BaseDto<ArticleDraftOut, ArticleDraft> {
    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
