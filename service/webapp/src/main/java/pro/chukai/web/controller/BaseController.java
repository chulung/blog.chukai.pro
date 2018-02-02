package pro.chukai.web.controller;

import pro.chukai.web.model.BaseComponent;
import pro.chukai.web.session.WebSessionSupport;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller基类
 *
 * @author chukai
 */
public abstract class BaseController extends BaseComponent {

    @Resource
    protected WebSessionSupport webSessionSupport;


    protected ResponseEntity success() {
        return success(MapUtils.EMPTY_MAP, HttpStatus.OK);
    }

    protected ResponseEntity success(Object key, Object value) {
        Map map = new HashMap();
        map.put(key, value);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    protected ResponseEntity success(Map body) {
        return new ResponseEntity(body, HttpStatus.OK);
    }
}