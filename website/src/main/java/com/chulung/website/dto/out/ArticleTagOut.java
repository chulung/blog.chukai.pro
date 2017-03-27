package com.chulung.website.dto.out;

import com.chulung.website.dto.BaseDto;
import com.chulung.website.model.ArticleTag;

/**
 * Created by chulung on 2017/3/25.
 */
public class ArticleTagOut extends ArticleTag implements BaseDto<ArticleTagOut, ArticleTag> {
    private Integer count;
}
