package com.tab.mint.device.sms;

import com.tab.mint.device.sms.SMSUtils.CPL.CPL;
import com.tab.mint.device.sms.SMSUtils.KDM.KDM;
import com.tab.mint.device.sms.SMSUtils.PlaybackStatus.PlaybackStatus;
import com.tab.mint.device.sms.SMSUtils.SMSInfo.SMSInfo;
import com.tab.mint.device.sms.SMSUtils.SPL.SPL;
import com.tab.mint.device.sms.SMSUtils.Schedule.Schedule;
import com.tab.mint.device.sms.SMSUtils.StorageInfo.StorageInfo;

import java.util.List;

/**
 * 服务器接口
 * <p>
 * Created by tab on 5/7/17.
 */
public interface SMS {

    boolean connected();

    CPL getCPL(String uuid);

    List<String> getCPLList();

    boolean deleteCPL(String uuid);

    SPL getSPL(String uuid);

    List<String> getSPLList();

    boolean createSPL(SPL spl);

    boolean deleteSPL(String uuid);

    boolean ejectSPL();

    boolean enableScheduler();

    boolean disableScheduler();

    boolean getScheduleStatus();

    String createSchedule(String dateTime, String SPLUuid);

    boolean cancelSchedule(String scheduleUtil);

    boolean validateCPL(String uuid);

    KDM getKDM(String uuid);

    List<String> getKDMList();

    boolean deleteKDM(String uuid);

    boolean play();

    boolean pause();

    boolean stop();

    boolean previous();

    boolean next();

    boolean move(int offsetPoint);

    boolean selectSPL(String SPLUuid);

    List<String> getCueList();

    boolean triggerCue(String cue);

    String getServerDateTime();

    SMSInfo getSMSInfo();

    StorageInfo getStorageInfo();

    String getPublicCertificate();

    String getPublicKeyThumbprint();

    String ingestCPL(String ftpIp, String port, String username, String password, String CPLUuid,
        String CPLLocation);

    String ingestKDM(String ftpIp, String port, String username, String password, String KDMUuid,
        String KDMLocation);

    String ingestKDM(String KDMContent);

    boolean cancelIngest(String uuid);

    String getIngestStatus(String uuid);

    PlaybackStatus getPlaybackStatus();

    Schedule getSchedule(String uuid);

    List<Schedule> getScheduleList();

}
