package com.tab.tms.client.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * Created by tab on 5/1/17.
 */
@Entity
public class RegisterInfo {
    @Id
    @GeneratedValue
    private Integer id;
    // 影院编码
    private Integer cinemaNumber;
    // 影院名称
    private String cinemaName;
    // CPU序列号
    private String CPUSerial;
    // MAC地址
    private Integer MACAddress;
    // 授权开始时间
    private Date BeforeExpiredDate;
    // 授权结束时间
    private Date AfterExpiredDate;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date UpdateTime;
    private boolean isDelete;

    public RegisterInfo(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCPUSerial() {
        return CPUSerial;
    }

    public void setCPUSerial(String CPUSerial) {
        this.CPUSerial = CPUSerial;
    }

    public Integer getMACAddress() {
        return MACAddress;
    }

    public void setMACAddress(Integer MACAddress) {
        this.MACAddress = MACAddress;
    }

    public Date getBeforeExpiredDate() {
        return BeforeExpiredDate;
    }

    public void setBeforeExpiredDate(Date beforeExpiredDate) {
        BeforeExpiredDate = beforeExpiredDate;
    }

    public Date getAfterExpiredDate() {
        return AfterExpiredDate;
    }

    public void setAfterExpiredDate(Date afterExpiredDate) {
        AfterExpiredDate = afterExpiredDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Integer getCinemaNumber() {
        return cinemaNumber;
    }

    public void setCinemaNumber(Integer cinemaNumber) {
        this.cinemaNumber = cinemaNumber;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
