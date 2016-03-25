package com.wck.dfs.controller;

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

@ControllerAdvice
public class GlobalControllerExceptionAdvice extends BaseController {

	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public @ResponseBody ModelMap maxxUploadSizeExceededException(MaxUploadSizeExceededException exceededException) {
		return errorMap().addAttribute("message", "文件过大");
	}

}
