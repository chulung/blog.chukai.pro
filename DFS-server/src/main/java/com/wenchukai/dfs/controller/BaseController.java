package com.wenchukai.dfs.controller;

import org.springframework.ui.ModelMap;

import com.wenchukai.base.BaseComponent;

public class BaseController extends BaseComponent {

	public ModelMap successMap() {
		return new ModelMap().addAttribute("success", 1);
	}

	public ModelMap errorMap() {
		return new ModelMap().addAttribute("success", 0);
	}

}
