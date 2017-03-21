package com.chulung.website.service;

import com.chulung.website.dto.out.SideBarInfo;
import com.chulung.website.dto.out.SiteFooteInfo;

/**
 * Created by chulung on 2017/3/21.
 */
public interface SiteService {
    SiteFooteInfo getSiteFooteInfo();

    SideBarInfo getSideBarInfo();
}
