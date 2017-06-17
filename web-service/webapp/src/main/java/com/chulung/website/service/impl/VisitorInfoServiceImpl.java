package com.chulung.website.service.impl;

import java.time.LocalDateTime;

import com.chulung.website.mapper.ArticleMapper;
import com.chulung.website.mapper.ArticleVisitMapper;
import com.chulung.website.model.ArticleVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.chulung.website.service.VisitorInfoService;

@Service
public class VisitorInfoServiceImpl extends BaseService implements VisitorInfoService {
    @Autowired
    private ArticleVisitMapper articleVisitMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void handArticleVisit(ArticleVisit articleVisitor) {
        checkExistBlank(articleVisitor.getArticleId(), articleVisitor.getSessionId());
        articleVisitor.setCreateTime(LocalDateTime.now());
        articleVisitor.setType(1);
        try {
            articleVisitMapper.insertSelective(articleVisitor);
            //自增阅读次数
            articleMapper.incrementVisitCount(articleVisitor.getArticleId());
        } catch (DuplicateKeyException e) {
            //igron
        }
    }

}
