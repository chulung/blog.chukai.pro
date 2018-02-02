package pro.chukai.web.service.impl;

import pro.chukai.web.dto.out.ArticleTagOut;
import pro.chukai.web.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.chukai.web.service.ArticleTagService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chukai on 2017/3/21.
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<ArticleTagOut> findAllArticleTag() {
        return articleTagMapper.selectAllTags().stream().map(articleTag -> new ArticleTagOut().buildFromModel(articleTag)).collect(Collectors.toList());
    }
}
