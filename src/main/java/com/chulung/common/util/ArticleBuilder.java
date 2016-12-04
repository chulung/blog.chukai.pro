package com.chulung.common.util;

import com.chulung.craft.model.Article;
import com.chulung.craft.model.ArticleDraft;
import com.chulung.craft.service.ColumnTypeSevice;
import org.apache.commons.lang3.StringEscapeUtils;
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
            String htmlContext = articleDraft.getHtmlContext();
            Pattern p = Pattern.compile("(https:)?//(\\w+\\.)?chulung.com.+?(\\.\\w{3})");
            Matcher m = p.matcher(htmlContext);
            if(m.find()){
                article.setPic(m.group());
            }
            article.setTypeName(this.columnTypeSevice.getIdColumnMap().get(articleDraft.getTypeId()).getCnName());
            article.setSummary(generatingSummary(htmlContext));
            article.setContext(StringEscapeUtils.unescapeHtml4(htmlContext));
            article.setUpdateTime(articleDraft.getUpdateTime());
            article.setAuthor(articleDraft.getAuthor());
            article.setIsDelete(articleDraft.getIsDelete());
            article.setTitle(articleDraft.getTitle());
            article.setCreateTime(LocalDateTime.now());
            article.setTypeId(articleDraft.getTypeId());
            article.setVersion(articleDraft.getVersion());
            return article;
    }
    public String generatingSummary(String context) {
        String replaceAll = context.replaceFirst("<h[1-9](.+)?</h[1-9]>","").replaceAll("</?.*?>", "");
        return replaceAll.length() > 100 ? replaceAll.substring(0, 97) + "..." : replaceAll;
    }
}
