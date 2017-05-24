package com.chulung.search.core;

import com.chulung.search.config.SearchConfig;
import com.chulung.SpringbootBaseTest;
import com.chulung.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chulung on 2016/11/10.
 */
public class SearchTest extends SpringbootBaseTest{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Search search;

    @Autowired
    private SearchConfig searchConfig;

    private SearchDocument doc;

    @Before
    public void setUp() {
        String context = TestUtil.readToString("src/test/resources/test.html").replaceAll("</?[^<]+>", "");
        String id = "1";
        String title = "什么是" + "函数" + "式编程";
        doc = new SearchDocument(id, title, context);
        //创建索引
        search.createIndex(doc);
    }

    @Test
    public void testSearch() throws Exception {
        String key = "函数";
        //搜索
        List<SearchDocument> result = search.search(key);
        assertThat(result.size()).isEqualTo(1);
        result.forEach(d -> {
            assertThat(d.getId()).isEqualTo(doc.getId());
            String titleHighLight = "什么是" + searchConfig.getHighlighterOpening() + "函数" + searchConfig.getHighlighterClosing() + "式编程";
            assertThat(d.getTitle()).isEqualTo(titleHighLight);
            //包含高亮的key
            String highLightKey = searchConfig.getHighlighterOpening() + key + searchConfig.getHighlighterClosing();
            assertThat(d.getContent().contains(highLightKey)).isTrue();
        });
    }
}