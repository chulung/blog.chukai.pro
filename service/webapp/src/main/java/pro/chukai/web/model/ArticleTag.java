package pro.chukai.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTag extends BaseModel implements Serializable {

    @Id
    private Integer id;
    private String tagName;
    private Integer articleId;

    @Transient
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
