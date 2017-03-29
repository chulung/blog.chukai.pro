package com.chulung.website.controller;

import com.chulung.website.session.WebSessionSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chulung.website.model.BaseComponent;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller基类
 *
 * @author chulung
 */
public abstract class BaseController extends BaseComponent {

    @Resource
    protected WebSessionSupport webSessionSupport;


    protected ResponseEntity success() {
        return new ResponseEntity(HttpStatus.OK);
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