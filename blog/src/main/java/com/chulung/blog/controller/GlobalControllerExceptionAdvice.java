package com.chulung.blog.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
/**
 * 全局异常处理
 * @author ChuKai
 *
 */

import com.chulung.blog.enumerate.LogLevel;
import com.chulung.blog.enumerate.LogType;
import com.chulung.blog.exception.MethodRuntimeExcetion;
import com.chulung.blog.mapper.AppLogMapper;
import com.chulung.blog.model.AppLog;
import com.chulung.blog.model.VisitorInfo;

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
	public @ResponseBody ModelMap Excetion(Exception excetion) {
		errorLog(excetion);
		HttpServletRequest request = this.currentRequest();
		if (request != null) {
			logger.error("VisitorInfo={}", new VisitorInfo(request));
		}
		AppLog record = new AppLog(LogType.EXCEPTION, LogLevel.ERROR, excetion.toString(), LocalDateTime.now());
		appLogMapper.insertSelective(record);
		return new ModelMap().addAttribute("error", "500");
	}

	protected HttpServletRequest currentRequest() throws IllegalStateException {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs == null ? null : attrs.getRequest();
	}
}
