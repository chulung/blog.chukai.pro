package com.chulung.search;

import com.chulung.csearch.core.CSearchDocument;
import com.chulung.craft.mapper.ArticleMapper;
import com.chulung.craft.model.Article;
import com.chulung.csearch.core.CSearchIndex;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chulung on 2016/11/10.
 */
@Component
public class ArticlesIndexHandler {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CSearchIndex cSearchIndex;

    public void resetIndex(){
        cSearchIndex.clearAll();
        index();
    }

    public void index(){
        List<Article> list;
        int pageNum = 1;
        PageHelper.startPage(pageNum,10);
        while (!(list=this.articleMapper.selectAll()).isEmpty()){
            list.forEach(article -> {
                CSearchDocument document=new CSearchDocument(article.getDocId(),article.getTitle(), article.getContext().replaceAll("</?[^<]+>", "").replaceAll("\\s+",""));
                cSearchIndex.createIndex(document);
            });
            pageNum++;
        }
    }
}
