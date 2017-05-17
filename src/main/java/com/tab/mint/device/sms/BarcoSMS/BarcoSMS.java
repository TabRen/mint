package com.tab.mint.device.sms.BarcoSMS;

import com.tab.mint.device.sms.SMS;
import com.tab.mint.device.sms.SMSUtils.CPL.CPL;
import com.tab.mint.device.sms.SMSUtils.GetPublicKeyUtil;
import com.tab.mint.device.sms.SMSUtils.Ingestion.Ingestion;
import com.tab.mint.device.sms.SMSUtils.KDM.KDM;
import com.tab.mint.device.sms.SMSUtils.PlaybackStatus.PlaybackStatus;
import com.tab.mint.device.sms.SMSUtils.SMSInfo.SMSInfo;
import com.tab.mint.device.sms.SMSUtils.SPL.SPL;
import com.tab.mint.device.sms.SMSUtils.Schedule.Schedule;
import com.tab.mint.device.sms.SMSUtils.StorageInfo.StorageInfo;
import com.tab.mint.device.sms.SMSUtils.XMLResponseUtil;
import com.tab.mint.enums.SMSEnum;
import com.tab.mint.exception.device.SMSException;
import com.tab.mint.utils.CheckConnectionUtil;
import org.crifst.project.tms.smi.barco.api.*;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * barco服务器类
 * <p>
 * Created by tab on 5/8/17.
 */
public class BarcoSMS implements SMS {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String ip;
    private int port;
    private String userName;
    private String password;

    BarcoSMS(String ip, String userName, String password) {
        this.ip = ip;
        this.port = 43758;
        this.userName = userName;
        this.password = password;
    }

    private ContentManagement contentManagement;
    private ShowManagement showManagement;
    private LicenseManagement licenseManagement;
    private SystemManagement systemManagement;
    private TransferManagement transferManagement;
    private PlaybackControl playbackControl;

    @Override
    public boolean connected() {
        return new CheckConnectionUtil().checkConnection(ip, port);
    }

    @Override
    public CPL getCPL(String uuid) {
        if (connected()) {
            contentManagement = new ContentManagement(ip, userName, password);
            try {
                String xml = contentManagement.getcpl(uuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
//                    xml = xmlResponseUtil.getXMLEltStrValue();
//                    CPL cpl = new CPL();
//                    cpl.setId(xmlResponseUtil.getXPathValue(xml,"//Id"));
                    return xmlResponseUtil.getCPL();
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getCPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public List<String> getCPLList() {
        if (connected()) {
            contentManagement = new ContentManagement(ip, userName, password);
            try {
                String xml = contentManagement.getcpllist();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getList("cpl_uuid");
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getCPLList occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public boolean deleteCPL(String uuid) {
        if (connected()) {
            contentManagement = new ContentManagement(ip, userName, password);
            try {
                String xml = contentManagement.deletecontent(uuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS deleteCPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public SPL getSPL(String uuid) {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.getpartialspl(uuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.parseSPLXML(xmlResponseUtil.getXMLEltStrValue());
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getSPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public List<String> getSPLList() {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.getspllist();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getList("show_uuid");
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getSPLList occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public boolean createSPL(SPL spl) {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.createspl(spl.toString());
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS createSPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean deleteSPL(String uuid) {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.deletespl(uuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS deleteSPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean ejectSPL() {
        return false;
    }

    @Override
    public boolean enableScheduler() {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.enablescheduler();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS enableSchedule occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean disableScheduler() {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.disablescheduler();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS disableScheduler occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean getScheduleStatus() {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.getschedulerstatus();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getScheduleStatus occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public String createSchedule(String dateTime, String SPLUuid) {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.createschedule(dateTime, SPLUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getXPathValue(xml, "//schedule_uuid");
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getScheduleStatus occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public boolean cancelSchedule(String scheduleUuid) {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.cancelschedule(scheduleUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status))
                    return true;
            } catch (Exception e) {
                logger.error("BarcoSMS cancelSchedule occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean validateCPL(String CPLUuid) {
        if (connected()) {
            contentManagement = new ContentManagement(ip, userName, password);
            try {
                String xml = contentManagement.validatecpl(CPLUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS validateCPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public KDM getKDM(String KDMUuid) {
        if (connected()) {
            licenseManagement = new LicenseManagement(ip, userName, password);
            try {
                String xml = licenseManagement.getkdm(KDMUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getKDM();
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getKDM occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public List<String> getKDMList() {
        licenseManagement = new LicenseManagement(ip, userName, password);
        try {
            String xml = licenseManagement.getkdmlist();
            XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
            String status = xmlResponseUtil.getResponsesStatus();
            if ("OK".equals(status)) {
                return xmlResponseUtil.getList("asset_uuid");
            }
        } catch (Exception e) {
            logger.error("BarcoSMS getKDMList occur exception: ", e);
            throw new SMSException(SMSEnum.INTERMAL_ERROR);
        }
        return null;
    }

    @Override
    public boolean deleteKDM(String KDMUuid) {
        if (connected()) {
            licenseManagement = new LicenseManagement(ip, userName, password);
            try {
                String xml = licenseManagement.deletekdm(KDMUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS deleteKDM occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean play() {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.playspl();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS play occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean pause() {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.pausespl();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS pause occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean stop() {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.stopspl();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS stop occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean previous() {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.previous();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS previous occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean next() {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.next();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS next occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean move(int offsetPoint) {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.gotoposition(offsetPoint);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS move occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public boolean selectSPL(String SPLUuid) {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.selectspl(SPLUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS selectSPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public List<String> getCueList() {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.getcuelist();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getList("cue");
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getCueList occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public boolean triggerCue(String cueLabel) {
        if (connected()) {
            if ((cueLabel != null) && (!Objects.equals(cueLabel, ""))) {
                playbackControl = new PlaybackControl(ip, userName, password);
                try {
                    String xml = playbackControl.triggercue(cueLabel);
                    XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                    String status = xmlResponseUtil.getResponsesStatus();
                    if ("OK".equals(status)) {
                        return true;
                    }
                } catch (Exception e) {
                    logger.error("BarcoSMS triggerCue occur exception: ", e);
                    throw new SMSException(SMSEnum.INTERMAL_ERROR);
                }
            }
        }
        return false;
    }

    @Override
    public String getServerDateTime() {
        if (connected()) {
            systemManagement = new SystemManagement(ip, userName, password);
            try {
                String xml = systemManagement.getserverdatetime();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getXPathValue(xml, "//iso_date_time");
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getServerDateTime occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public SMSInfo getSMSInfo() {
        if (connected()) {
            systemManagement = new SystemManagement(ip, userName, password);
            try {
                String xml = systemManagement.getserverinfo();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    SMSInfo smsInfo = new SMSInfo();
                    smsInfo.setModel(xmlResponseUtil.getXPathValue(xml, "//model"));
                    smsInfo.setSerial(xmlResponseUtil.getXPathValue(xml, "//serial"));
                    smsInfo.setOsVersion(xmlResponseUtil.getXPathValue(xml, "//version/@os"));
                    smsInfo.setSoftwareVersion(
                        xmlResponseUtil.getXPathValue(xml, "//version/@software"));
                    smsInfo.setFirmwareVersion(
                        xmlResponseUtil.getXPathValue(xml, "//version/@firmware"));
                    return smsInfo;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getSMSInfo occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public StorageInfo getStorageInfo() {
        if (connected()) {
            systemManagement = new SystemManagement(ip, userName, password);
            try {
                String xml = systemManagement.getstorageinfo();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    StorageInfo storageInfo = new StorageInfo();
                    storageInfo.setTotalSpace(
                        xmlResponseUtil.getXPathValue(xml, "//storage/@total_space"));
                    storageInfo
                        .setFreeSpace(xmlResponseUtil.getXPathValue(xml, "//storage/@free_space"));
                    return storageInfo;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getStorageInfo occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public String getPublicCertificate() {
        if (connected()) {
            systemManagement = new SystemManagement(ip, userName, password);
            try {
                String xml = systemManagement.getpubliccertificate();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getXPathValue(xml, "//certificate");
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getPublicCertificate occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public String getPublicKeyThumbprint() {
        // systemManagement.getpublickeythumbprint()在barco-tms.jar中未实现
        //String xml = systemManagement.getpublickeythumbprint();
        String publicCertificate = getPublicCertificate();
        if ((publicCertificate != null) && (!Objects.equals(publicCertificate, ""))) {
            GetPublicKeyUtil getPublicKeyUtil = new GetPublicKeyUtil();
            return getPublicKeyUtil.getThumbPrint(publicCertificate);
        }
        return null;
    }

    @Override
    public String ingestCPL(String ftpIp, String ftpPort, String loginUser, String loginPassword,
        String CPLUuid, String CPLLocation) {
        if (connected()) {
            transferManagement = new TransferManagement(ip, userName, password);
            try {
                String xml = transferManagement
                    .ingestpackage(ftpIp, ftpPort, loginUser, loginPassword, CPLLocation, CPLUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getXMLEltStrValue();
                }
            } catch (Exception e) {
                logger.error("BarcoSMS ingestCPL occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public String ingestKDM(String ftpIp, String ftpPort, String loginUser, String loginPassword,
        String KDMUuid, String KDMLocation) {
        if (connected()) {
            transferManagement = new TransferManagement(ip, userName, password);
            try {
                String xml = transferManagement
                    .ingestkdm(ftpIp, ftpPort, loginUser, loginPassword, KDMLocation, KDMUuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getXMLEltStrValue();
                }
            } catch (Exception e) {
                logger.error("BarcoSMS ingestKDM by FTP occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public String ingestKDM(String KDMContent) {
        if (connected()) {
            transferManagement = new TransferManagement(ip, userName, password);
            try {
                String xml = transferManagement.ingestkdm(KDMContent);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return xmlResponseUtil.getXMLEltStrValue();
                }
            } catch (Exception e) {
                logger.error("BarcoSMS ingestKDM occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public boolean cancelIngest(String uuid) {
        if (connected()) {
            transferManagement = new TransferManagement(ip, userName, password);
            try {
                String xml = transferManagement.cancelingest(uuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS cancelIngest occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return false;
    }

    @Override
    public String getIngestStatus(String uuid) {
        if (connected()) {
            transferManagement = new TransferManagement(ip, userName, password);
            try {
                String xml = transferManagement.getingeststatus(uuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    Ingestion ingestion = new Ingestion(xmlResponseUtil.getXMLEltStrValue());
                    return ingestion.getStatus();
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getIngestStatus occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public PlaybackStatus getPlaybackStatus() {
        if (connected()) {
            playbackControl = new PlaybackControl(ip, userName, password);
            try {
                String xml = playbackControl.getplaybackstatus();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    PlaybackStatus playbackStatus = new PlaybackStatus();
                    playbackStatus
                        .setPlaybackMode(xmlResponseUtil.getXPathValue(xml, "//playbackmode"));
                    playbackStatus.setStatus(xmlResponseUtil.getXPathValue(xml, "//@state"));
                    playbackStatus.setShowUuid(xmlResponseUtil.getXPathValue(xml, "//show_uuid"));
                    playbackStatus.setShowName(xmlResponseUtil.getXPathValue(xml, "//show_name"));
                    playbackStatus.setSPLPlayedDuration(
                        xmlResponseUtil.getXPathValue(xml, "//show_position/@played_duration"));
                    playbackStatus.setSPLTotalDuration(
                        xmlResponseUtil.getXPathValue(xml, "//show_position/@total_duration"));
                    playbackStatus.setCPLUuid(xmlResponseUtil.getXPathValue(xml, "//cpl_uuid"));
                    playbackStatus.setCPLName(xmlResponseUtil.getXPathValue(xml, "//cpl_name"));
                    playbackStatus.setCPLPlayedDuration(
                        xmlResponseUtil.getXPathValue(xml, "//cpl_position/@played_duration"));
                    playbackStatus.setCPLIndex(
                        xmlResponseUtil.getXPathValue(xml, "//cpl_position/@cpl_index"));
                    playbackStatus.setCPLTotalDuration(
                        xmlResponseUtil.getXPathValue(xml, "//cpl_position/@total_duration"));
                    return playbackStatus;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getIngestStatus occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public Schedule getSchedule(String uuid) {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.getschedule(uuid);
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    Schedule schedule = new Schedule();
                    schedule.setUuid(xmlResponseUtil.getXPathValue(xml, "//schedule"));
                    schedule.setShowUuid(xmlResponseUtil.getXPathValue(xml, "//@show_uuid"));
                    schedule.setIsoDateTime(xmlResponseUtil.getXPathValue(xml, "//@iso_date_time"));
                    return schedule;
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getSchedule occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

    @Override
    public List<Schedule> getScheduleList() {
        if (connected()) {
            showManagement = new ShowManagement(ip, userName, password);
            try {
                String xml = showManagement.getschedulelist();
                XMLResponseUtil xmlResponseUtil = new XMLResponseUtil(xml);
                String status = xmlResponseUtil.getResponsesStatus();
                if ("OK".equals(status)) {
                    List xPathList = xmlResponseUtil.getXPathList(xml, "//schedule[@show_uuid]");
                    if (!xPathList.isEmpty()) {
                        List<Schedule> scheduleList = new ArrayList<>();
                        for (Object obj : xPathList) {
                            DefaultElement dElt = (DefaultElement) obj;
                            Schedule schedule = new Schedule();
                            schedule.setShowUuid(
                                xmlResponseUtil.getXPathValue(dElt.asXML(), "//@show_uuid"));
                            schedule.setIsoDateTime(
                                xmlResponseUtil.getXPathValue(dElt.asXML(), "//@iso_date_time"));
                            schedule
                                .setUuid(xmlResponseUtil.getXPathValue(dElt.asXML(), "//schedule"));
                            scheduleList.add(schedule);
                        }
                        return scheduleList;
                    }
                }
            } catch (Exception e) {
                logger.error("BarcoSMS getScheduleList occur exception: ", e);
                throw new SMSException(SMSEnum.INTERMAL_ERROR);
            }
        }
        return null;
    }

}
