package com.chulung.blog.exception;

/**
 * 全局方法校验异常
 * 
 * @author ChuKai
 *
 */
public class MethodValidateExcetion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276005706555502837L;

	private int code;

	public MethodValidateExcetion() {
	}

	public MethodValidateExcetion(String message) {
		this(-1,message);
	}
	public MethodValidateExcetion(int code, String message) {
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
