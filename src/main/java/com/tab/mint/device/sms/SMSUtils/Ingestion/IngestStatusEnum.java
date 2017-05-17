package com.tab.mint.device.sms.SMSUtils.Ingestion;

/**
 * Created by tab on 5/15/17.
 */
public enum IngestStatusEnum {
    COMPLETED(0, "传输已完成"), ERROR(-1, "传输失败"), RUNNING(1, "传输进行中"), CANCELED(2, "传输已取消");

    private Integer code;
    private String msg;

    IngestStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
