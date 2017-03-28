package com.chulung.website.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by chulung on 2016/11/6.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PageNotFoundException extends  RuntimeException{
}
