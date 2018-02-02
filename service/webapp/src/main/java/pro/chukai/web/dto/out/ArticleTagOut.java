package pro.chukai.web.dto.out;

import pro.chukai.web.dto.BaseDto;
import pro.chukai.web.model.ArticleTag;
import pro.chukai.web.dto.BaseDto;
import pro.chukai.web.model.ArticleTag;

/**
 * Created by chukai on 2017/3/25.
 */
public class ArticleTagOut extends ArticleTag implements BaseDto<ArticleTagOut, ArticleTag> {
    private Integer count;
}
