package com.wck.dfs.controller;

import org.springframework.ui.ModelMap;

import com.wck.bean.BaseComponent;

public class BaseController extends BaseComponent {

	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}

	public ModelMap errorMap() {
		return new ModelMap().addAttribute("success", 0);
	}

}
