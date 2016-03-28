package com.wenchukai.smelting.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenchukai.smelting.exception.GlobalMethodRuntimeExcetion;

@ControllerAdvice
public class GlobalControllerExceptionAdvice extends BaseController {


	@ExceptionHandler(GlobalMethodRuntimeExcetion.class)
	public @ResponseBody ModelMap GlobalMethodRuntimeExcetion(GlobalMethodRuntimeExcetion excetion) {
		return new ModelMap().addAttribute("success", excetion.getCode()).addAttribute("message", excetion.getMessage());
	}
}
