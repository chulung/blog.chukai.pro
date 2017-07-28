package com.chulung.search.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 相关配置信息
 * Created by chulung on 2016/11/7.
 */
@ConfigurationProperties(prefix = "search")
@Component
public class SearchConfig implements InitializingBean {
    /**
     * 索引存储目录
     */
    private String storePath;

    private DirectoryType directoryType;
    /**
     * 高亮标签
     */
    private String highlighterOpening = "<em>";

    /**
     * 高亮标签
     */
    private String highlighterClosing = "</em>";

    /**
     * 搜索结果片段长度
     */
    private Integer fragmentSize;

    private Formatter highLighterFormatter;

    public DirectoryType getDirectoryType() {
        return directoryType;
    }

    public void setDirectoryType(DirectoryType directoryType) {
        this.directoryType = directoryType;
    }

    public Integer getFragmentSize() {
        return fragmentSize;
    }

    public void setFragmentSize(Integer fragmentSize) {
        this.fragmentSize = fragmentSize;
    }


    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getHighlighterOpening() {
        return highlighterOpening;
    }

    public void setHighlighterOpening(String highlighterOpening) {
        this.highlighterOpening = highlighterOpening;
    }

    public String getHighlighterClosing() {
        return highlighterClosing;
    }

    public void setHighlighterClosing(String highlighterClosing) {
        this.highlighterClosing = highlighterClosing;
    }

    public Formatter getHighLighterFormatter() {
        return highLighterFormatter;
    }

    public void setHighLighterFormatter(Formatter highLighterFormatter) {
        this.highLighterFormatter = highLighterFormatter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!this.isRamDirectory() && StringUtils.isBlank(storePath))
            throw new IllegalArgumentException("storePath can't be empty");
        this.highLighterFormatter = new SimpleHTMLFormatter(this.highlighterOpening, this.highlighterClosing);
        if (fragmentSize == null) {
            fragmentSize = 150;
        }
    }

    private boolean isRamDirectory() {
        return this.directoryType == DirectoryType.RAM;
    }
}
