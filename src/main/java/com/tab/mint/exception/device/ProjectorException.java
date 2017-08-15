package com.tab.mint.exception.device;

import com.tab.mint.enums.ProjectorEnum;
import com.tab.mint.enums.SMSEnum;

/**
 * Created by tab on 5/9/17.
 */
public class ProjectorException extends RuntimeException {

    private Integer code;

    public ProjectorException(ProjectorEnum projectorEnum) {
        super(projectorEnum.getMsg());
        this.code = projectorEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
