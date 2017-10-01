package com.wchukai.web.dto.in;

import com.wchukai.web.dto.BaseDto;
import com.wchukai.web.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleIn extends Article implements BaseDto<ArticleIn, Article> {

    private static final long serialVersionUID = -6414652104447372040L;

    private LocalDateTime createTimeStart;

    private LocalDateTime createTimeEnd;

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public LocalDateTime getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(LocalDateTime createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

}
