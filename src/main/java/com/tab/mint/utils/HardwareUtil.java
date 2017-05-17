package com.tab.mint.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取mac地址和cpu序列号
 * Created by tab on 4/30/17.
 */
@Component
public class HardwareUtil {
    private static final Logger logger = LoggerFactory.getLogger(HardwareUtil.class);

    /**
     * 获取CPU序列号
     *
     * @return cpuSerial
     */
    public String getCPUSerial() throws Exception {
        List<String> cmd = new ArrayList<String>();
        String keyStr;
        if (systemType().contains("windows")) {
            cmd.add("wmic");
            cmd.add("cpu");
            cmd.add("get");
            cmd.add("ProcessorId");
            keyStr = "";
        } else {
            cmd.add("sudo");
            cmd.add("dmidecode");
            keyStr = "ID:";
        }
        return exec(cmd, keyStr);
    }

    /**
     * 获取mac地址
     *
     * @return macAddr
     */
    public String getMACAddress() throws Exception {
        List<String> cmd = new ArrayList<String>();
        String keyStr;
        if (systemType().contains("windows")) {
            cmd.add("ipconfig");
            cmd.add("/all");
            keyStr = "";
        } else {
            cmd.add("ifconfig");
            keyStr = "HWaddr";
        }
        return exec(cmd, keyStr);
    }

    /**
     * 调用系统命令
     *
     * @param cmd    :系统命令
     * @param keyStr :要截取的关键字
     * @return result
     */
    private String exec(List<String> cmd, String keyStr) throws Exception {
        ProcessBuilder pb;
        pb = new ProcessBuilder(cmd);
        String outputStr;
        String result;
        result = null;
        //正则配置所有特殊字符和空格
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";
        Process process = pb.start();
        BufferedReader stdInput =
            new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError =
            new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((outputStr = stdInput.readLine()) != null) {
            logger.info(outputStr);
            if (outputStr.contains(keyStr)) {
                result = outputStr.substring(outputStr.indexOf(keyStr) + keyStr.length() + 1)
                    .replaceAll(regEx, "");
                break;
            }
        }
        while ((outputStr = stdError.readLine()) != null) {
            logger.info(outputStr);
        }
        return result;
    }

    /**
     * 确定系统类型
     *
     * @return systemType
     */
    private String systemType() {
        String systemType = System.getProperty("os.name").toLowerCase();
        logger.info("os.name: {}", systemType);
        return systemType;
    }
}

