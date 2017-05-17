package com.tab.mint.exception;

import com.tab.mint.enums.MintEnum;

/**
 * Created by tab on 5/9/17.
 */
public class MintException extends RuntimeException {

    private Integer code;

    public MintException(MintEnum mintEnum) {
        super(mintEnum.getMsg());
        this.code = mintEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
