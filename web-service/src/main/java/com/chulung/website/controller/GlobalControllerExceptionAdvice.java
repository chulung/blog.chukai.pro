package com.chulung.website.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
/**
 * 全局异常处理
 * @author chulung
 *
 */

import com.chulung.website.enumerate.LogLevel;
import com.chulung.website.enumerate.LogType;
import com.chulung.website.exception.MethodRuntimeExcetion;
import com.chulung.website.mapper.AppLogMapper;
import com.chulung.website.model.AppLog;
import com.chulung.website.model.VisitorInfo;

@ControllerAdvice
public class GlobalControllerExceptionAdvice extends BaseController {

	@Autowired
	private AppLogMapper appLogMapper;

	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public @ResponseBody ModelMap maxxUploadSizeExceededException(MaxUploadSizeExceededException exceededException) {
		return errorMap().addAttribute("message", "文件过大");
	}

	@ExceptionHandler(MethodRuntimeExcetion.class)
	public @ResponseBody ModelMap GlobalMethodRuntimeExcetion(MethodRuntimeExcetion excetion) {
		return new ModelMap().addAttribute("success", excetion.getCode()).addAttribute("message",
				excetion.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody ModelMap Excetion(Exception excetion) throws IOException {
		//忽略参数类型异常
		if (excetion instanceof MethodArgumentTypeMismatchException || excetion instanceof HttpRequestMethodNotSupportedException) {
			return new ModelMap().addAttribute("error", "501");
		}
		errorLog(excetion);
		ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
		excetion.printStackTrace(new PrintWriter(buf, true));
		String expMessage = buf.toString();
		buf.close();
		HttpServletRequest request = this.currentRequest();
		if (request != null) {
			expMessage=new VisitorInfo(request)+":\n"+expMessage;
		}
		AppLog record = new AppLog(LogType.EXCEPTION, LogLevel.ERROR,expMessage, LocalDateTime.now());
		appLogMapper.insertSelective(record);
		return new ModelMap().addAttribute("error", "500");
	}

	protected HttpServletRequest currentRequest() throws IllegalStateException {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs == null ? null : attrs.getRequest();
	}
}
