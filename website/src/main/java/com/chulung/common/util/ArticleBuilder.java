package com.chulung.common.util;

import com.chulung.website.model.Article;
import com.chulung.website.model.ArticleDraft;
import com.chulung.website.service.ColumnTypeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ArticleBuilder {
    @Autowired
    private ColumnTypeSevice columnTypeSevice;
    public  Article buildeFromDraft(ArticleDraft articleDraft){
            Article article = new Article();
            article.setId(articleDraft.getArticleId());
            String htmlContent = articleDraft.getHtmlContent();
            Pattern p = Pattern.compile("(https:)?//(\\w+\\.)?chulung.com.+?(\\.\\w{3})");
            Matcher m = p.matcher(htmlContent);
            if(m.find()){
                article.setPic(m.group());
            }
            article.setTypeName(this.columnTypeSevice.getIdColumnMap().get(articleDraft.getTypeId()).getCnName());
            article.setSummary(generatingSummary(htmlContent));
            article.setContent(htmlContent);
            article.setUpdateTime(articleDraft.getUpdateTime());
            article.setAuthor(articleDraft.getAuthor());
            article.setIsDelete(articleDraft.getIsDelete());
            article.setTitle(articleDraft.getTitle());
            article.setCreateTime(LocalDateTime.now());
            article.setTypeId(articleDraft.getTypeId());
            article.setVersion(articleDraft.getVersion());
            article.setTags(articleDraft.getTags());
            return article;
    }
    public String generatingSummary(String content) {
        String replaceAll = content.replaceFirst("<h[1-9](.+)?</h[1-9]>","").replaceAll("</?.*?>", "");
        return replaceAll.length() > 100 ? replaceAll.substring(0, 97) + "..." : replaceAll;
    }
}
