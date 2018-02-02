package pro.chukai.web.service.impl;

import pro.chukai.web.exception.HttpStatusException;
import pro.chukai.web.model.BaseComponent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public abstract class BaseService extends BaseComponent {

    protected void checkExistBlank(Object... params) {
        for (Object param : params) {
            if (param == null ? true : param instanceof String ? StringUtils.isBlank(param.toString()) : false) {
                logger.error(StringUtils.join(params, ','));
                throw HttpStatusException.of(HttpStatus.BAD_REQUEST, "必填参数为空");
            }
        }
    }
}
