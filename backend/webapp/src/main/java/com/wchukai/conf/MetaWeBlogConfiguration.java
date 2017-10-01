package com.wchukai.conf;

import com.wchukai.metaweblog.MetaWeblog;
import com.wchukai.metaweblog.config.ConfigInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 博客推送配置
 */
@Configuration
@ConfigurationProperties(prefix = "metaweblog")
public class MetaWeBlogConfiguration {

    private List<ConfigInfo> configInfos = new ArrayList<>();

    private List<String> list = new ArrayList<String>();

    @Bean
    public List<MetaWeblog> metaWeblogs() {
        if (CollectionUtils.isEmpty(configInfos)) return Collections.emptyList();
        return configInfos.stream().map(c -> {
            MetaWeblog metaWeblog = new MetaWeblog();
            metaWeblog.setConfigInfo(c);
            return metaWeblog;
        }).collect(Collectors.toList());
    }

    public List<ConfigInfo> getConfigInfos() {
        return configInfos;
    }

    public List<String> getList() {
        return list;
    }
}
