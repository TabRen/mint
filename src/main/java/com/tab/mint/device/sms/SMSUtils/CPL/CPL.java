package com.tab.mint.device.sms.SMSUtils.CPL;

import java.util.List;

/**
 * CPL
 * <p>
 * Created by tab on 5/10/17.
 */
public class CPL {

    private String Id;

    private String AnnotationText;

    private String IssueDate;

    private String Issuer;

    private String Creator;

    private String ContentTitleText;

    private String ContentKind;

    private String RatingList;

    private List<ReelList> ReelList;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public List<com.tab.mint.device.sms.SMSUtils.CPL.ReelList> getReelList() {
        return ReelList;
    }

    public void setReelList(List<com.tab.mint.device.sms.SMSUtils.CPL.ReelList> reelList) {
        ReelList = reelList;
    }

    public String getAnnotationText() {
        return AnnotationText;
    }

    public void setAnnotationText(String annotationText) {
        AnnotationText = annotationText;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public String getIssuer() {
        return Issuer;
    }

    public void setIssuer(String issuer) {
        Issuer = issuer;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getContentTitleText() {
        return ContentTitleText;
    }

    public void setContentTitleText(String contentTitleText) {
        ContentTitleText = contentTitleText;
    }

    public String getContentKind() {
        return ContentKind;
    }

    public void setContentKind(String contentKind) {
        ContentKind = contentKind;
    }

    public String getRatingList() {
        return RatingList;
    }

    public void setRatingList(String ratingList) {
        RatingList = ratingList;
    }
}
