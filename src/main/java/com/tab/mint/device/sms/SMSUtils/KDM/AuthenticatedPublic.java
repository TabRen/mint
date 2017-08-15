package com.tab.mint.device.sms.SMSUtils.KDM;

import java.util.List;

/**
 * Created by tab on 5/13/17.
 */
public class AuthenticatedPublic {
    private String messageId;
    private String messageType;
    private String annotationText;
    private String issueDate;
    RequiredExtensions requiredExtensions;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getAnnotationText() {
        return annotationText;
    }

    public void setAnnotationText(String annotationText) {
        this.annotationText = annotationText;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public RequiredExtensions getRequiredExtensions() {
        return requiredExtensions;
    }

    public void setRequiredExtensions(RequiredExtensions requiredExtensions) {
        this.requiredExtensions = requiredExtensions;
    }
}
