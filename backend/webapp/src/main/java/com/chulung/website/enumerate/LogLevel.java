package com.chulung.website.enumerate;

/**
 * 日志级别枚举
 *
 * @author chulung1
 */
public enum LogLevel {
    ERROR(0, "error"), INFO(1, "info");
    private int value;
    private String desc;

    private LogLevel(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

}