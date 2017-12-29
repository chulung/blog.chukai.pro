package com.wchukai.web.dto;

import com.wchukai.web.enumerate.ResultCodeEnum;

/**
 * @author chukai
 */
public class Result {
    private Object data;
    private int code;
    private String msg;

    public Result(Object data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

    public Result(Object data, ResultCodeEnum resultCodeEnum) {
        this.data = data;
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Result success(Object data) {
        return new Result(data, ResultCodeEnum.SUCCESS);
    }

    public static Result of(ResultCodeEnum resultCodeEnum) {
        return new Result(resultCodeEnum);
    }
}
