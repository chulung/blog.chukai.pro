package pro.chukai.web.service;

import pro.chukai.web.enumerate.ConfigKeyEnum;
import pro.chukai.web.model.Config;
import pro.chukai.web.model.Config;

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
