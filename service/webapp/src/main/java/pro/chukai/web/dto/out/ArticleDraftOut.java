package pro.chukai.web.dto.out;

import pro.chukai.web.dto.BaseDto;
import pro.chukai.web.model.ArticleDraft;

/**
 * Created by chukai on 2017/3/28.
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
