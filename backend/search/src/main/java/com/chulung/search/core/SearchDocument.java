package com.chulung.search.core;

/**
 * Created by chulung on 2016/11/10.
 */
public class SearchDocument {
    public static final String ID = "id";
    public static  final  String TITLE="title";
    public  static  final  String CONTEXT="content";
    /**
     * 唯一ID，创建前会根据id删除已有的
     */
    private String id;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档内容
     */
    private String content;

    public SearchDocument(String id, String title, String content){
        if (id==null) throw new IllegalArgumentException("id can't be null!");
        if (title==null) throw new IllegalArgumentException("title can't be null!");
        if (content ==null) throw new IllegalArgumentException("content can't be null!");
        this.id=id;
        this.title=title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SearchDocument{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
