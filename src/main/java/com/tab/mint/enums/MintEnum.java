package com.tab.mint.enums;

/**
 * 系统异常
 * <p>
 * Created by tab on 5/9/17.
 */
public enum MintEnum {

    SUCCESS(100, "成功"), UNKNOW_ERROR(101, "未知错误"), INTERMAL_ERROR(102, "内部错误"), PARSE_XML_ERROR(103,
        "解析xml文件出错"), DISCONNECT_SOCKET(104, "无法建立socket连接");
    private Integer code;
    private String msg;

    MintEnum(Integer code, String msg) {
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
