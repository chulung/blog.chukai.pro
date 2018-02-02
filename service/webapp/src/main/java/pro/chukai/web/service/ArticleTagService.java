package pro.chukai.web.service;

import pro.chukai.web.dto.out.ArticleTagOut;

import java.util.List;

/**
 * Created by chukai on 2017/3/21.
 */
public interface ArticleTagService {
    List<ArticleTagOut> findAllArticleTag();
}
