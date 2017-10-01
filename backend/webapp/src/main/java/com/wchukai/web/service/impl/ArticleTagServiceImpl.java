package com.wchukai.web.service.impl;

import com.wchukai.web.dto.out.ArticleTagOut;
import com.wchukai.web.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wchukai on 2017/3/21.
 */
@Service
public class ArticleTagServiceImpl implements com.wchukai.web.service.ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<ArticleTagOut> findAllArticleTag() {
        return articleTagMapper.selectAllTags().stream().map(articleTag -> new ArticleTagOut().buildFromModel(articleTag)).collect(Collectors.toList());
    }
}
