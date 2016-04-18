package com.wenchukai.blog.controller;

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

import com.wenchukai.blog.exception.GlobalMethodRuntimeExcetion;
import com.wenchukai.common.base.BaseController;

@ControllerAdvice
public class GlobalControllerExceptionAdvice extends BaseController {

	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public @ResponseBody ModelMap maxxUploadSizeExceededException(MaxUploadSizeExceededException exceededException) {
		return errorMap().addAttribute("message", "文件过大");
	}

	@ExceptionHandler(GlobalMethodRuntimeExcetion.class)
	public @ResponseBody ModelMap GlobalMethodRuntimeExcetion(GlobalMethodRuntimeExcetion excetion) {
		return new ModelMap().addAttribute("success", excetion.getCode()).addAttribute("message", excetion.getMessage());
	}
}
