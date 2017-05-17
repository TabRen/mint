package com.tab.mint.device.sms.SMSUtils.SPL;

import java.util.List;

/**
 * Created by tab on 5/10/17.
 */
public class Clip {
    private String type;

    private String id;

    private String contentTitleText;

    private String durationInMilliseconds;

    private String contentKind;

    private List<CueList> cueList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentTitleText() {
        return contentTitleText;
    }

    public void setContentTitleText(String contentTitleText) {
        this.contentTitleText = contentTitleText;
    }

    public String getDurationInMilliseconds() {
        return durationInMilliseconds;
    }

    public void setDurationInMilliseconds(String durationInMilliseconds) {
        this.durationInMilliseconds = durationInMilliseconds;
    }

    public String getContentKind() {
        return contentKind;
    }

    public void setContentKind(String contentKind) {
        this.contentKind = contentKind;
    }

    public List<CueList> getCueList() {
        return cueList;
    }

    public void setCueList(List<CueList> cueList) {
        this.cueList = cueList;
    }
}
