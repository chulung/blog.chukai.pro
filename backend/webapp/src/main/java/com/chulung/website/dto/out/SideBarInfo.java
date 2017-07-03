package com.chulung.website.dto.out;

import com.chulung.website.model.Article;
import com.chulung.website.model.Comment;

import java.util.List;

/**
 * Created by chulung on 2017/3/25.
 */
public class SideBarInfo {
    private List<Archive> archives;
    private List<Article> recentUpdateArticles;
    private List<Comment> recentlyComments;

    public List<Archive> getArchives() {
        return archives;
    }

    public void setArchives(List<Archive> archives) {
        this.archives = archives;
    }

    public void setRecentUpdateArticles(List<Article> recentUpdateArticles) {
        this.recentUpdateArticles = recentUpdateArticles;
    }

    public void setRecentlyComments(List<Comment> recentlyComments) {
        this.recentlyComments = recentlyComments;
    }

    public List<Article> getRecentUpdateArticles() {
        return recentUpdateArticles;
    }

    public List<Comment> getRecentlyComments() {
        return recentlyComments;
    }
}
