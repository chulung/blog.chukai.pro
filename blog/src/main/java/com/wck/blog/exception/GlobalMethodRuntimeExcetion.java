package com.wck.blog.exception;

/**
 * 全局方法执行异常
 * 
 * @author ChuKai
 *
 */
public class GlobalMethodRuntimeExcetion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276005706555502837L;

	private int code;

	public GlobalMethodRuntimeExcetion() {
	}

	public GlobalMethodRuntimeExcetion(int code, String message) {
		super(message);
		this.setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
