package com.wchukai.web.service;

import com.wchukai.web.dto.out.SideBarInfo;
import com.wchukai.web.dto.out.SiteFooteInfo;

/**
 * Created by wchukai on 2017/3/21.
 */
public interface SiteService {
    SiteFooteInfo getSiteFooteInfo();

    SideBarInfo getSideBarInfo();
}
