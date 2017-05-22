package com.chulung.website.service;

import com.chulung.BaseTest;
import com.chulung.website.enumerate.ConfigKeyEnum;
import com.chulung.website.model.Config;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigServiceTest extends BaseTest {

    @Autowired
    private ConfigService configService;

    @Test
    public void create() throws Exception {
        this.configService.create(new Config(ConfigKeyEnum.ARTICLE_LICENSE, "aaa"));
        assertThat(this.configService.getValueBykey(ConfigKeyEnum.ARTICLE_LICENSE)).isNotNull();
    }

    public void getValueBykey() throws Exception {
        assertThat(this.configService.getValueBykey(ConfigKeyEnum.RESET_SEARCH_INDEX, "false")).isNotNull();
        assertThat(this.configService.getValueBykey(ConfigKeyEnum.RESET_SEARCH_INDEX)).isNotNull();
    }

    @Test
    public void updateByKey() throws Exception {
        this.configService.updateByKey(new Config(ConfigKeyEnum.RESET_SEARCH_INDEX,"true"));
        assertThat(this.configService.getValueBykey(ConfigKeyEnum.RESET_SEARCH_INDEX)).isEqualTo("true");
    }

}