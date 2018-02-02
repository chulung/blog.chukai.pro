package pro.chukai.web.service;

import pro.chukai.web.model.Column;
import pro.chukai.web.model.Column;

import java.util.List;
import java.util.Map;

/**
 * Created by chukai on 2016/11/6.
 */
public interface ColumnSevice {

    List<Column> getAllColumns();

    Map<Integer, Column> getIdColumnMap();

    Map<String, Column> getEnNameColumnMap();
}
