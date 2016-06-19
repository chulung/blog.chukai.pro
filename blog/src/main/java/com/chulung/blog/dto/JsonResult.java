package com.chulung.blog.dto;

public class JsonResult<T> {
	private int code;
	private T result;
	private String msg;

	private JsonResult(int code, T result, String msg) {
		this.code = code;
		this.result = result;
		this.msg = msg;
	}

	public JsonResult<T> ofSuccess(String msg) {
		return ofSuccess(null, msg);
	}

	public JsonResult<T> ofSuccess(T result) {
		return ofSuccess(result, null);
	}

	public JsonResult<T> ofSuccess(T result, String msg) {
		return new JsonResult<>(1, result, msg);
	}

	public JsonResult<T> ofFailure(String msg) {
		return ofFailure(null, msg);
	}

	public JsonResult<T> ofFailure(T result, String msg) {
		return new JsonResult<>(0, result, msg);
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getResult() {
		return result;
	}

}
