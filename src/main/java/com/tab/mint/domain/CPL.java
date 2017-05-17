package com.tab.mint.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * CPL信息表
 * <p>
 * Created by tab on 5/10/17.
 */
@Entity
public class CPL {
    @Id
    @GeneratedValue
    private Integer Id;

    private String Uuid;

    private String AnnotationText;

    private Date IssueDate;

    private String Issuer;

    private String Creator;

    private String ContentTitleText;

    private String ContentKind;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    public CPL() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public String getAnnotationText() {
        return AnnotationText;
    }

    public void setAnnotationText(String annotationText) {
        AnnotationText = annotationText;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date issueDate) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
