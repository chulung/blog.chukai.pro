package com.chulung.search.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 相关配置信息
 * Created by chulung on 2016/11/7.
 */
@ConfigurationProperties(prefix = "csearch")
@Component
public class CSearchConfig implements InitializingBean {
    /**
     * 索引存储目录
     */
    private String indexStorePath;

    /**
     * 高亮标签
     */
    private String highlighterOpening;

    /**
     * 高亮标签
     */
    private String highlighterClosing;

    /**
     * 搜索结果片段长度
     */
    private Integer fragmentSize;

    private Formatter highLighterFormatter;

    /**
     * 用于构建环境
     */
    private boolean ramDirectory=false;

    public boolean isRamDirectory() {
        return ramDirectory;
    }

    public void setRamDirectory(boolean ramDirectory) {
        this.ramDirectory = ramDirectory;
    }

    public Integer getFragmentSize() {
        return fragmentSize;
    }

    public void setFragmentSize(Integer fragmentSize) {
        this.fragmentSize = fragmentSize;
    }


    public String getIndexStorePath() {
        return indexStorePath;
    }

    public void setIndexStorePath(String indexStorePath) {
        this.indexStorePath = indexStorePath;
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
        if (!this.isRamDirectory() && StringUtils.isBlank(indexStorePath)) throw  new IllegalArgumentException("indexStorePath can't be empty");
        if (StringUtils.isBlank(this.highlighterOpening)||StringUtils.isBlank(this.highlighterClosing)){
            this.highLighterFormatter=new SimpleHTMLFormatter("<em>","</em>");
        }else {
            this.highLighterFormatter=new SimpleHTMLFormatter(this.highlighterOpening,this.highlighterClosing);
        }
        if (fragmentSize==null){
            fragmentSize=150;
        }
    }
}
