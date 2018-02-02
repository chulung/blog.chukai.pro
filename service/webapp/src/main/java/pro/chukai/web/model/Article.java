package pro.chukai.web.model;

import pro.chukai.common.databind.LocalDateTimeDeserializer;
import pro.chukai.common.databind.LocalDateTimeSerializer;
import pro.chukai.web.enumerate.IsDeleteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pro.chukai.web.enumerate.IsDeleteEnum;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article extends BaseModel {

    private static final long serialVersionUID = -1692088776366835421L;

    private String title;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    private String author;

    private Integer columnId;

    private String derivationUrl;

    private Integer version;

    private IsDeleteEnum isDelete;

    private String content;

    private Integer commentCount;

    private Integer visitCount;

    private String pic;

    private String license;

    private String summary;

    private Integer indexRank;

    private String tags;

    private String columnName;

    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public String getDerivationUrl() {
        return derivationUrl;
    }

    public void setDerivationUrl(String derivationUrl) {
        this.derivationUrl = derivationUrl == null ? null : derivationUrl.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public IsDeleteEnum getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(IsDeleteEnum isDelete) {
        this.isDelete = isDelete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIndexRank() {
        return indexRank;
    }

    public void setIndexRank(Integer indexRank) {
        this.indexRank = indexRank;
    }
}