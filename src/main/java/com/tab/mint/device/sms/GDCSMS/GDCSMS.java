package com.tab.mint.device.sms.GDCSMS;

import com.sun.jmx.snmp.daemon.CommunicationException;
import com.tab.mint.device.sms.SMS;
import com.tab.mint.device.sms.SMSUtils.CPL.CPL;
import com.tab.mint.device.sms.SMSUtils.KDM.KDM;
import com.tab.mint.device.sms.SMSUtils.PlaybackStatus.PlaybackStatus;
import com.tab.mint.device.sms.SMSUtils.SMSInfo.SMSInfo;
import com.tab.mint.device.sms.SMSUtils.SPL.SPL;
import com.tab.mint.device.sms.SMSUtils.Schedule.Schedule;
import com.tab.mint.device.sms.SMSUtils.StorageInfo.StorageInfo;
import com.tab.mint.utils.CheckConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

/**
 * GDC服务器实现类
 * <p>
 * Created by tab on 5/7/17.
 */
public class GDCSMS implements SMS {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private transient Socket socket;

    private String ip;

    private int port;

    private boolean connected = false;

    public GDCSMS(String ip, int port) {
        this.ip = ip;
        this.port = port;
        connected = connected();
    }

    private static final byte[] GDC_HEADER =
        {0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0F, 0x15, 0x01, 0x10, 0x00, 0x00, 0x00,
            0x00};

    @Autowired
    private CheckConnectionUtil checkConnectionUtil;

    private void _connect() {
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(ip, port); // 获取sockaddress对象
            socket.connect(socketAddress, 3000);
            socket.setKeepAlive(true);
            socket.setSoTimeout(15000);
        } catch (IOException e) {
            throw new CommunicationException(e);
        }
    }

    private void _disconnect() {
        try {
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (IOException e) {
            throw new CommunicationException(e);
        }
    }

    private byte[] calculate(String data) {
        byte[] length = new byte[4];
        int value = data.length();
        length[0] = (byte) 0x83;
        for (int i = 3; i > 0; --i) {
            length[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return length;
    }

    @Override
    public boolean connected() {
        return new CheckConnectionUtil().checkConnection(ip, port);
    }

    @Override
    public CPL getCPL(String uuid) {
        return null;
    }

    @Override
    public List<String> getCPLList() {
        return null;
    }

    @Override
    public boolean deleteCPL(String uuid) {
        return false;
    }

    @Override
    public SPL getSPL(String uuid) {
        return null;
    }

    @Override
    public List<String> getSPLList() {
        return null;
    }

    @Override
    public boolean createSPL(SPL spl) {
        return false;
    }

    @Override
    public boolean deleteSPL(String uuid) {
        return false;
    }

    @Override
    public boolean ejectSPL() {
        return false;
    }

    @Override
    public boolean enableScheduler() {
        return false;
    }

    @Override
    public boolean disableScheduler() {
        return false;
    }

    @Override
    public boolean getScheduleStatus() {
        return false;
    }

    @Override
    public String createSchedule(String dateTime, String SPLUuid) {
        return null;
    }

    @Override
    public boolean validateCPL(String uuid) {
        return false;
    }

    @Override
    public KDM getKDM(String uuid) {
        return null;
    }

    @Override
    public List<String> getKDMList() {
        return null;
    }

    @Override
    public boolean deleteKDM(String uuid) {
        return false;
    }

    @Override
    public boolean play() {
        return false;
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean previous() {
        return false;
    }

    @Override
    public boolean next() {
        return false;
    }

    @Override
    public boolean move(int offsetPoint) {
        return false;
    }

    @Override
    public boolean selectSPL(String SPLUuid) {
        return false;
    }

    @Override
    public List<String> getCueList() {
        return null;
    }

    @Override
    public boolean triggerCue(String cue) {
        return false;
    }

    @Override
    public String getServerDateTime() {
        return null;
    }

    @Override
    public SMSInfo getSMSInfo() {
        return null;
    }

    @Override
    public StorageInfo getStorageInfo() {
        return null;
    }

    @Override
    public String getPublicCertificate() {
        return null;
    }

    @Override
    public String getPublicKeyThumbprint() {
        return null;
    }

    @Override
    public String ingestCPL(String ftpIp, String port, String username, String password,
        String CPLUuid, String CPLLocation) {
        return null;
    }

    @Override
    public String ingestKDM(String ftpIp, String port, String username, String password,
        String KDMUuid, String KDMLocation) {
        return null;
    }

    @Override
    public String ingestKDM(String KDMContent) {
        return null;
    }

    @Override
    public boolean cancelIngest(String uuid) {
        return false;
    }

    @Override
    public String getIngestStatus(String uuid) {
        return null;
    }

    @Override
    public PlaybackStatus getPlaybackStatus() {
        return null;
    }

    @Override
    public Schedule getSchedule(String uuid) {
        return null;
    }

    @Override
    public List<Schedule> getScheduleList() {
        return null;
    }

    @Override
    public boolean cancelSchedule(String scheduleUuid) {
        return false;
    }
}
