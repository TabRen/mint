package com.tab.mint.enums;

/**
 * Created by tab on 5/9/17.
 */
public enum ProjectorEnum {

    SUCCESS(300, "成功"), NKNOW_ERROR(301, "未知错误"), INTERMAL_ERROR(302, "内部错误");
    private Integer code;
    private String msg;

    ProjectorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
