package com.chulung.website.service.impl;

import com.chulung.website.dto.out.ArticleTagOut;
import com.chulung.website.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chulung on 2017/3/21.
 */
@Service
public class ArticleTagServiceImpl implements com.chulung.website.service.ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<ArticleTagOut> findAllArticleTag() {
        return articleTagMapper.selectAllTags().stream().map(articleTag -> new ArticleTagOut().buildFromModel(articleTag)).collect(Collectors.toList());
    }
}
