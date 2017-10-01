package com.wchukai.web.enumerate;

public enum LogType {
    CRON_JOB_LOG(0, "定时器日志"), META_CK_BLOG_LOG(1, "metackblog推送日志"), EXCEPTION(2, "服务器异常日志");

    private int value;
    private String desc;

    private LogType(int value, String desc) {
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
