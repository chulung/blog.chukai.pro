package com.chulung.website.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by chulung on 2017/3/29.
 */
public class FileUploadExcetion extends HttpStatusException {
    protected FileUploadExcetion(HttpStatus status) {
        super(status);
    }

    protected FileUploadExcetion(HttpStatus status, String message) {
        super(status, message);
    }
}
