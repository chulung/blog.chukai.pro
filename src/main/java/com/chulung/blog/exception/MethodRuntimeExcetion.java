package com.chulung.blog.exception;

import java.io.IOException;

/**
 * 全局方法校验异常
 * 
 * @author ChuKai
 *
 */
public class MethodRuntimeExcetion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276005706555502837L;

	private int code = 0;

	public MethodRuntimeExcetion() {
	}

	public MethodRuntimeExcetion(String message) {
		this(0, message);
	}

	public MethodRuntimeExcetion(int code, String message) {
		super(message);
		this.setCode(code);
	}

	public MethodRuntimeExcetion(IOException e) {
		super(e);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
