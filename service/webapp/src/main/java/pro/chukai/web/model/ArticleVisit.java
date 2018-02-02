package pro.chukai.web.model;

import java.time.LocalDateTime;

/**
 * Created by chukai on 2017/6/17.
 */
public class ArticleVisit extends BaseModel {
    private Integer articleId;
    private String sessionId;
    private Integer type;
    private LocalDateTime createTime;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
