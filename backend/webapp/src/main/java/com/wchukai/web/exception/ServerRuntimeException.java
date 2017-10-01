package com.wchukai.web.exception;

/**
 * Created by wchukai on 2017/5/9.
 */
public class ServerRuntimeException extends RuntimeException {
    public ServerRuntimeException(Throwable cause) {
        super(cause);
    }

    public ServerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
