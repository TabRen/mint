package com.tab.mint.device.sms.BarcoSMS;

import com.tab.mint.device.sms.SMSUtils.CPL.CPL;
import com.tab.mint.device.sms.SMSUtils.KDM.KDM;
import com.tab.mint.device.sms.SMSUtils.PlaybackStatus.PlaybackStatus;
import com.tab.mint.device.sms.SMSUtils.SMSInfo.SMSInfo;
import com.tab.mint.device.sms.SMSUtils.SPL.Clip;
import com.tab.mint.device.sms.SMSUtils.SPL.ClipList;
import com.tab.mint.device.sms.SMSUtils.SPL.SPL;
import com.tab.mint.device.sms.SMSUtils.Schedule.Schedule;
import com.tab.mint.device.sms.SMSUtils.StorageInfo.StorageInfo;
import org.junit.Test;

import java.util.List;

/**
 * BarcoSMS测试类
 * <p>
 * Created by tab on 5/10/17.
 */
public class BarcoSMSTest {

  @Test
  public void getCPLTest() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    CPL cpl = barcoSMS.getCPL("urn:uuid:ec3bc2ff-2255-45d5-b113-ff1b03d9534c");
    System.out.println("cplInfo getAnnotationText(): " + cpl.getAnnotationText());
  }

  @Test
  public void getCPLListTest() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    List<String> list = barcoSMS.getCPLList();
    for (int i = 0; i < list.size(); i++)
      System.out.println("index: " + i + ", value: " + list.get(i));
  }

  @Test
  public void getSPLTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    SPL spl = barcoSMS.getSPL("urn:uuid:cc478312-8add-4a74-8c5b-ed240f8de1ce");
    //        SPL spl = barcoSMS.getSPL("urn:uuid:fa3e03b9-4a68-4e03-b03c-f4f666a6b67c");
    System.out.println("splInfo spl.getContentTitleText(): " + spl.getContentTitleText());
    for (ClipList clipList : spl.getClipList()) {
      for (Clip clip : clipList.getClip()) {
        System.out.println("splInfo getClip.getContentTitleTest(): " + clip.getContentTitleText());
      }
    }
  }

  @Test
  public void getSPLListTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    List<String> list = barcoSMS.getSPLList();
    for (int i = 0; i < list.size(); i++)
      System.out.println("index: " + i + ", value: " + list.get(i));
  }

  @Test
  public void getKDMTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    KDM kdm = barcoSMS.getKDM("urn:uuid:18156535-df60-458b-b497-abb20d868b9f");
    System.out.println("AnnotationText: " + kdm.getAuthenticatedPublic().getAnnotationText());
  }

  @Test
  public void getKDMListTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    List<String> list = barcoSMS.getKDMList();
    for (int i = 0; i < list.size(); i++)
      System.out.println("index: " + i + ", value: " + list.get(i));
  }

  @Test
  public void getCueListTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    List<String> list = barcoSMS.getCueList();
    for (String str : list) {
      System.out.println("cue: " + str);
    }
  }

  @Test
  public void getServerDateTime() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    String isoDateTime = barcoSMS.getServerDateTime();
    System.out.println("BarcoSMS getServerDateTime: " + isoDateTime);
  }

  @Test
  public void getPublicKeyTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    String str = barcoSMS.getPublicCertificate();
    System.out.println("BarcoSMS getPublicKey: " + str);
  }

  @Test
  public void getPublicKeyThumbprintTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    String publicKeyThumbprint = barcoSMS.getPublicKeyThumbprint();
    System.out.println("BarcoSMS getPublicKeyThumbprint: " + publicKeyThumbprint);
  }

  @Test
  public void getPlaybackStatusTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    PlaybackStatus str = barcoSMS.getPlaybackStatus();
    System.out.println("BarcoSMS getPlaybackStatus: " + str.getStatus());
  }

  @Test
  public void getScheduleListTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    System.out.println("BarcoSMS getScheduleList: ");
    for (Schedule schedule : barcoSMS.getScheduleList()) {
      System.out.println(schedule.getUuid());
    }
  }

  @Test
  public void getScheduleTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    System.out.println("BarcoSMS getSchedule: ");
    Schedule schedule = barcoSMS.getSchedule("urn:uuid:be9b756e-12d1-41f0-b167-48585961547a");
    System.out.println(schedule.getUuid());
    System.out.println(schedule.getShowUuid());
    System.out.println(schedule.getIsoDateTime());
  }

  @Test
  public void getSMSInfoTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    SMSInfo smsInfo = barcoSMS.getSMSInfo();
    System.out.println("BarcoSMS getSMSInfo: " + smsInfo.getModel());
    System.out.println("BarcoSMS getSMSInfo: " + smsInfo.getSerial());
    System.out.println("BarcoSMS getSMSInfo: " + smsInfo.getOsVersion());
    System.out.println("BarcoSMS getSMSInfo: " + smsInfo.getSoftwareVersion());
    System.out.println("BarcoSMS getSMSInfo: " + smsInfo.getFirmwareVersion());
  }

  @Test
  public void getStorageInfoTEST() {
    BarcoSMS barcoSMS = new BarcoSMS("10.50.1.23", "admin", "Admin1234");
    StorageInfo storageInfo = barcoSMS.getStorageInfo();
    System.out.println("BarcoSMS getStorageInfo total_space: " + storageInfo.getTotalSpace());
    System.out.println("BarcoSMS getStorageInfo free_space: " + storageInfo.getFreeSpace());
  }

}