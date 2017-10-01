package com.wchukai.web.service;

import com.wchukai.web.enumerate.ConfigKeyEnum;
import com.wchukai.web.model.Config;

public interface ConfigService {

    public String getValueBykey(ConfigKeyEnum key);

    public Config create(Config config);

    /**
     * 查询value 没有则按defaultValue创建一个
     *
     * @param key
     * @param defaultValue
     * @return
     */
    String getValueBykey(ConfigKeyEnum key, String defaultValue);

    public boolean updateByKey(Config config);
}
