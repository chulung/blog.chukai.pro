package com.wchukai.web.service;

import com.wchukai.test.SpringbootBaseTest;
import com.wchukai.web.model.Column;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by wchukai on 2017/3/23.
 */
public class ColumnSeviceTest extends SpringbootBaseTest {
    @Autowired
    private ColumnSevice columnSevice;

    @Test
    public void getColumns() throws Exception {
        List<Column> columns = this.columnSevice.getAllColumns();
        assertThat(columns).isNotEmpty();
        Map<String, Column> enNameMap = this.columnSevice.getEnNameColumnMap();
        assertThat(enNameMap).isNotEmpty();
        Map<Integer, Column> idMap = this.columnSevice.getIdColumnMap();
        assertThat(idMap).isNotEmpty();
        columns.stream().forEach(c -> {
            assertThat(enNameMap.get(c.getEnName()).getEnName()).isEqualTo(c.getEnName());
            Column column = idMap.get(c.getId());
            assertThat(column.getId()).isEqualTo(c.getId());
        });
    }

}