package com.tab.mint.device.sms.SMSUtils.Ingestion;

/**
 *
 * Created by tab on 5/15/17.
 */
public class Ingestion {
    private String uuid;
    private String ingestUuid;
    private String status;
    private String percent;
    private String description;

    public Ingestion(String xml) {
        // TODO
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIngestUuid() {
        return ingestUuid;
    }

    public void setIngestUuid(String ingestUuid) {
        this.ingestUuid = ingestUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
