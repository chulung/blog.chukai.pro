package com.wchukai.web.dto.out;

import com.wchukai.web.dto.BaseDto;
import com.wchukai.web.model.ArticleTag;

/**
 * Created by wchukai on 2017/3/25.
 */
public class ArticleTagOut extends ArticleTag implements BaseDto<ArticleTagOut, ArticleTag> {
    private Integer count;
}
