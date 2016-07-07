package com.chulung.blog.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
/**
 * 全局异常处理
 * @author ChuKai
 *
 */

import com.chulung.blog.enumerate.LogLevel;
import com.chulung.blog.enumerate.LogType;
import com.chulung.blog.exception.MethodValidateExcetion;
import com.chulung.blog.mapper.AppLogMapper;
import com.chulung.blog.model.AppLog;

@ControllerAdvice
public class GlobalControllerExceptionAdvice extends BaseController {

	@Autowired
	private AppLogMapper appLogMapper;

	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public @ResponseBody ModelMap maxxUploadSizeExceededException(MaxUploadSizeExceededException exceededException) {
		return errorMap().addAttribute("message", "文件过大");
	}

	@ExceptionHandler(MethodValidateExcetion.class)
	public @ResponseBody ModelMap GlobalMethodRuntimeExcetion(MethodValidateExcetion excetion) {
		return new ModelMap().addAttribute("success", excetion.getCode()).addAttribute("message",
				excetion.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody ModelMap Excetion(Exception excetion) {
		errorLog(excetion);
		AppLog record = new AppLog(LogType.EXCEPTION, LogLevel.ERROR, excetion.toString(), LocalDateTime.now());
		appLogMapper.insertSelective(record);
		return new ModelMap().addAttribute("error", "500");
	}
}
