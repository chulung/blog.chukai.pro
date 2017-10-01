package com.wchukai.web.dto.out;

import com.wchukai.web.dto.BaseDto;
import com.wchukai.web.model.ArticleDraft;

/**
 * Created by wchukai on 2017/3/28.
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
