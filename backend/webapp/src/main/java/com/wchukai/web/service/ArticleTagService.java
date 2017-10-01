package com.wchukai.web.service;

import com.wchukai.web.dto.out.ArticleTagOut;

import java.util.List;

/**
 * Created by wchukai on 2017/3/21.
 */
public interface ArticleTagService {
    List<ArticleTagOut> findAllArticleTag();
}
