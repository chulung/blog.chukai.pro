package pro.chukai.web.dto.out;

import java.util.List;

/**
 * Created by chukai on 2017/3/25.
 */
public class SiteFooteInfo {

    private List<ArticleTagOut> tags;
    private List<ArticleOut> recommendedArticles;

    public List<ArticleTagOut> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTagOut> tags) {
        this.tags = tags;
    }

    public List<ArticleOut> getRecommendedArticles() {
        return recommendedArticles;
    }

    public void setRecommendedArticles(List<ArticleOut> recommendedArticles) {
        this.recommendedArticles = recommendedArticles;
    }
}
