package pro.chukai.web.service;

import pro.chukai.web.dto.out.SideBarInfo;
import pro.chukai.web.dto.out.SiteFooteInfo;

/**
 * Created by chukai on 2017/3/21.
 */
public interface SiteService {
    SiteFooteInfo getSiteFooteInfo();

    SideBarInfo getSideBarInfo();
}
