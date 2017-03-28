package com.chulung.website.service;

import com.chulung.website.dto.out.ArticleTagOut;

import java.util.List;

/**
 * Created by chulung on 2017/3/21.
 */
public interface ArticleTagService {
    List<ArticleTagOut> findAllArticleTag();
}
