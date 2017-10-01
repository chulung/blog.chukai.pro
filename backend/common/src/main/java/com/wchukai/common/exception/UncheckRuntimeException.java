package com.wchukai.common.exception;

/**
 * Created by wchukai on 2017/7/9.
 */
public class UncheckRuntimeException extends RuntimeException {
    public UncheckRuntimeException(Exception e) {
        super(e);
    }
}
