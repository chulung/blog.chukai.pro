package com.chulung.website.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chulung.website.exception.FileUploadExcetion;
import com.chulung.website.exception.HttpStatusException;
import com.chulung.website.exception.ServerRuntimeException;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import com.chulung.website.enumerate.LogLevel;
import com.chulung.website.enumerate.LogType;
import com.chulung.website.mapper.AppLogMapper;
import com.chulung.website.model.AppLog;
import com.chulung.website.model.VisitorInfo;

@ControllerAdvice
public class GlobalExceptionAdvice extends BaseController {

    @Autowired
    private AppLogMapper appLogMapper;

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public
    @ResponseBody
    ResponseEntity<Map<String, Object>> maxxUploadSizeExceededException(MaxUploadSizeExceededException excetion) {
        return new ResponseEntity<Map<String, Object>>(new ModelMap().addAttribute("success", 0).addAttribute("message", "文件过大"), HttpStatus.REQUEST_ENTITY_TOO_LARGE);
    }

    @ExceptionHandler(value = FileUploadExcetion.class)
    public
    @ResponseBody
    ResponseEntity<Map<String, Object>> fileUploadExcetion(FileUploadExcetion excetion) {
        return new ResponseEntity<Map<String, Object>>(new ModelMap().addAttribute("success", 0).addAttribute("message", excetion.getMessage()), excetion.getStatus());
    }

    @ExceptionHandler(HttpStatusException.class)
    public
    @ResponseBody
    ResponseEntity httpStatusException(HttpStatusException excetion) {
        return new ResponseEntity(MapUtils.EMPTY_MAP, excetion.getStatus())
                ;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public
    @ResponseBody
    ResponseEntity httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException excetion) {
        return new ResponseEntity(MapUtils.EMPTY_MAP, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public
    @ResponseBody
    ResponseEntity methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException excetion) {
        return new ResponseEntity(MapUtils.EMPTY_MAP, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ServerRuntimeException.class, Exception.class})
    public
    @ResponseBody
    ResponseEntity excetion(Exception excetion) throws IOException {
        errorLog(excetion);
        ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        excetion.printStackTrace(new PrintWriter(buf, true));
        String expMessage = buf.toString();
        buf.close();
        HttpServletRequest request = this.currentRequest();
        if (request != null) {
            expMessage = new VisitorInfo(request) + ":\n" + expMessage;
        }
        AppLog record = new AppLog(LogType.EXCEPTION, LogLevel.ERROR, expMessage, LocalDateTime.now());
        appLogMapper.insertSelective(record);
        return new ResponseEntity(MapUtils.EMPTY_MAP, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected HttpServletRequest currentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs == null ? null : attrs.getRequest();
    }
}
