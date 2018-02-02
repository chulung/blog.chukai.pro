package pro.chukai.web.mapper;

import pro.chukai.mybatis.mapper.BaseMapper;
import pro.chukai.web.model.ArticleTag;
import pro.chukai.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * Created by chukai on 2017/2/18.
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<ArticleTag> selectAllTags();
}
