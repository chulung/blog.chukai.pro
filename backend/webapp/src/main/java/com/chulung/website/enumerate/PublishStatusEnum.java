package com.chulung.website.enumerate;

public enum PublishStatusEnum {
    Y(1, "发布"), N(0, "未发布");
    private Integer code;
    private String dedcription;

    private PublishStatusEnum(Integer code, String dedcription) {
        this.code = code;
        this.dedcription = dedcription;
    }

    public Integer getCode() {
        return code;
    }

    public String getDedcription() {
        return dedcription;
    }

}
