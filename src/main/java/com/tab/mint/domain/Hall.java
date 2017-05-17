package com.tab.mint.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 厅信息表
 *
 * Created by tab on 5/1/17.
 */
@Entity
public class Hall {

    @Id
    @GeneratedValue
    private Integer id;

    // 影厅编号
    private Integer hallNumber;

    // 影厅名称
    private String hallName;

    // 服务器类型
    private String serverType;

    // 主服务器IP
    private String serverMasterIP;

    // 从服务器IP
    private String serverSlaveIP;

    // 主放映机IP
    private String projectorMasterIP;

    // 从放映机IP
    private String projectorSlaveIP;

    // 解码器IP
    private String processorIP;

    // 自动化控制器IP
    private String automationIP;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date UpdateTime;

    public Hall() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerMasterIP() {
        return serverMasterIP;
    }

    public void setServerMasterIP(String serverMasterIP) {
        this.serverMasterIP = serverMasterIP;
    }

    public String getServerSlaveIP() {
        return serverSlaveIP;
    }

    public void setServerSlaveIP(String serverSlaveIP) {
        this.serverSlaveIP = serverSlaveIP;
    }

    public String getProjectorMasterIP() {
        return projectorMasterIP;
    }

    public void setProjectorMasterIP(String projectorMasterIP) {
        this.projectorMasterIP = projectorMasterIP;
    }

    public String getProjectorSlaveIP() {
        return projectorSlaveIP;
    }

    public void setProjectorSlaveIP(String projectorSlaveIP) {
        this.projectorSlaveIP = projectorSlaveIP;
    }

    public String getProcessorIP() {
        return processorIP;
    }

    public void setProcessorIP(String processorIP) {
        this.processorIP = processorIP;
    }

    public String getAutomationIP() {
        return automationIP;
    }

    public void setAutomationIP(String automationIP) {
        this.automationIP = automationIP;
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

}
