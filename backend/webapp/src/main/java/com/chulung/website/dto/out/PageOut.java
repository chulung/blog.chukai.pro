package com.chulung.website.dto.out;

import java.util.List;

/**
 * Created by chulung on 2017/3/25.
 */
public class PageOut<T> {
    public static final PageOut<ArticleOut> EMPTY = new PageOut<>();
    private List<T> list;
    private Integer page;
    private Integer prePage;
    private Integer nextPage;
    private Integer totalPage;

    public PageOut(Integer page, Integer totalPage) {
        this.page = page;
        this.totalPage = totalPage;
        this.prePage = page == 1 ? null : page - 1;
        this.nextPage = page < totalPage ? page + 1 : null;
    }

    public PageOut(List<T> list) {
        this.list = list;
    }

    public PageOut(Integer page) {
        this.page = page;
    }

    public PageOut() {

    }

    public PageOut(int page, int totalPage, List<T> list) {
        this(page, totalPage);
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
