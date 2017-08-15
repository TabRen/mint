package com.tab.mint.device.sms.SMSUtils.PlaybackStatus;

/**
 * 播放状态
 * <p>
 * Created by tab on 5/15/17.
 */
public class PlaybackStatus {
    private String playbackMode;
    private String status;
    private String showUuid;
    private String showName;
    private String SPLPlayedDuration;
    private String SPLTotalDuration;
    private String CPLUuid;
    private String CPLName;
    private String CPLPlayedDuration;
    private String CPLIndex;
    private String CPLTotalDuration;

    public String getSPLPlayedDuration() {
        return SPLPlayedDuration;
    }

    public void setSPLPlayedDuration(String SPLPlayedDuration) {
        this.SPLPlayedDuration = SPLPlayedDuration;
    }

    public String getSPLTotalDuration() {
        return SPLTotalDuration;
    }

    public void setSPLTotalDuration(String SPLTotalDuration) {
        this.SPLTotalDuration = SPLTotalDuration;
    }

    public String getCPLPlayedDuration() {
        return CPLPlayedDuration;
    }

    public void setCPLPlayedDuration(String CPLPlayedDuration) {
        this.CPLPlayedDuration = CPLPlayedDuration;
    }

    public String getCPLIndex() {
        return CPLIndex;
    }

    public void setCPLIndex(String CPLIndex) {
        this.CPLIndex = CPLIndex;
    }

    public String getCPLTotalDuration() {
        return CPLTotalDuration;
    }

    public void setCPLTotalDuration(String CPLTotalDuration) {
        this.CPLTotalDuration = CPLTotalDuration;
    }

    public String getPlaybackMode() {
        return playbackMode;
    }

    public void setPlaybackMode(String playbackMode) {
        this.playbackMode = playbackMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShowUuid() {
        return showUuid;
    }

    public void setShowUuid(String showUuid) {
        this.showUuid = showUuid;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getCPLUuid() {
        return CPLUuid;
    }

    public void setCPLUuid(String CPLUuid) {
        this.CPLUuid = CPLUuid;
    }

    public String getCPLName() {
        return CPLName;
    }

    public void setCPLName(String CPLName) {
        this.CPLName = CPLName;
    }

}
