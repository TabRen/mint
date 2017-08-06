package com.tab.mint.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 影院信息表
 * <p>
 * Created by tab on 5/1/17.
 */
@Entity
public class Cinema {

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
    private String MACAddress;

    // 授权开始时间
    private Date beforeExpiredDate;

    // 授权结束时间
    private Date afterExpiredDate;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    public Cinema() {
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

    public String getMACAddress() {
        return MACAddress;
    }

    public void setMACAddress(String MACAddress) {
        this.MACAddress = MACAddress;
    }

    public Date getBeforeExpiredDate() {
        return beforeExpiredDate;
    }

    public void setBeforeExpiredDate(Date beforeExpiredDate) {
        this.beforeExpiredDate = beforeExpiredDate;
    }

    public Date getAfterExpiredDate() {
        return afterExpiredDate;
    }

    public void setAfterExpiredDate(Date afterExpiredDate) {
        this.afterExpiredDate = afterExpiredDate;
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
