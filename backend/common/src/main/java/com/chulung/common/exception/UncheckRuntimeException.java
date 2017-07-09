package com.chulung.common.exception;

/**
 * Created by chulung on 2017/7/9.
 */
public class UncheckRuntimeException extends RuntimeException {
    public UncheckRuntimeException(Exception e) {
        super(e);
    }
}
