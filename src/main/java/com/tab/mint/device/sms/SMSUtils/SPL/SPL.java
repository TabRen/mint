package com.tab.mint.device.sms.SMSUtils.SPL;

import java.util.List;

/**
 * SPLInfo
 * <p>
 * Created by tab on 5/10/17.
 */
public class SPL {

    private String attributeName;

    private String attributeValue;

    private String uuid;

    private String contentTitleText;

    private String annotationText;

    private String issuer;

    private String issueDate;

    private String creator;

    private List<ClipList> clipList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContentTitleText() {
        return contentTitleText;
    }

    public void setContentTitleText(String contentTitleText) {
        this.contentTitleText = contentTitleText;
    }

    public String getAnnotationText() {
        return annotationText;
    }

    public void setAnnotationText(String annotationText) {
        this.annotationText = annotationText;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<ClipList> getClipList() {
        return clipList;
    }

    public void setClipList(List<ClipList> clipList) {
        this.clipList = clipList;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

}
