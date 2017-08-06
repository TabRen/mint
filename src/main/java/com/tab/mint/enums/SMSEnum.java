package com.tab.mint.enums;

/**
 * Created by tab on 5/9/17.
 */
public enum SMSEnum {

    SUCCESS(200, "成功"), NKNOW_ERROR(201, "未知错误"), INTERMAL_ERROR(202, "内部错误"), DISCONNECTED(203,
        "服务器未连接"), CANCEL_SCHEDULE_ERROR(204, "取消排期失败"), VALIDATECPL_FAILED(205, "CPL验证失败");;

    private Integer code;
    private String msg;

    SMSEnum(Integer code, String msg) {
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
