package com.tab.mint.exception.device;

import com.tab.mint.enums.SMSEnum;

/**
 * Created by tab on 5/9/17.
 */
public class SMSException extends RuntimeException {

    private Integer code;

    public SMSException(SMSEnum SMSEnum) {
        super(SMSEnum.getMsg());
        this.code = SMSEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
