package com.tab.mint.service;

import com.tab.mint.domain.Cinema;
import com.tab.mint.utils.HardwareUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 验证服务器有效性
 * <p>
 * Created by tab on 5/1/17.
 */
public class ValidateService {
    @Autowired
    private Cinema cinema;

    @Autowired
    private HardwareUtil hardwareUtil;

    public Boolean valid() {
        try {
            // 更新数据库
            cinema.setCPUSerial(hardwareUtil.getCPUSerial());
            cinema.setMACAddress(hardwareUtil.getMACAddress());

            Integer cinemaNumber = cinema.getCinemaNumber();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String beforeExpiredDate = dateFormat.format(cinema.getBeforeExpiredDate());
            String afterExpiredDate = dateFormat.format(cinema.getAfterExpiredDate());
            String CPUSerial = cinema.getCPUSerial();
            String MACAddress = cinema.getMACAddress();

            // 发post请求到yard

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
