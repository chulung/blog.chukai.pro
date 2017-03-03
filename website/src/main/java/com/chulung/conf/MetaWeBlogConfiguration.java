package com.chulung.conf;

import com.chulung.metaweblog.MetaWeblog;
import com.chulung.metaweblog.config.ConfigInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 博客推送配置
 */
@Configuration
@ConfigurationProperties(prefix = "metaweblog")
public class MetaWeBlogConfiguration {

    private List<ConfigInfo> configInfos;

    @Bean
    public List<MetaWeblog> metaWeblogs() {
        if (CollectionUtils.isEmpty(configInfos)) return Collections.emptyList();
        return configInfos.stream().map(c -> {
            MetaWeblog metaWeblog = new MetaWeblog();
            metaWeblog.setConfigInfo(c);
            return metaWeblog;
        }).collect(Collectors.toList());
    }
}
