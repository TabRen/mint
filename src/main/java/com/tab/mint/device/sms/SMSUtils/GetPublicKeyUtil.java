package com.tab.mint.device.sms.SMSUtils;

import com.tab.mint.enums.MintEnum;
import com.tab.mint.exception.MintException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.cert.X509Certificate;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 用x509证书获得公钥指纹
 * <p>
 * Created by tab on 5/15/17.
 */
public class GetPublicKeyUtil {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String getThumbPrint(String x509Certificate) {
        InputStream inputStream = new ByteArrayInputStream(x509Certificate.getBytes());
        try {
            X509Certificate x509Cert = X509Certificate.getInstance(inputStream);
            String[] subject = x509Cert.getSubjectDN().toString().split("\"");
            return subject[1];
        } catch (Exception e) {
            logger.error("GetPublicKeyThumbprintUtil getThumbPrint occur exception: ", e);
            throw new MintException(MintEnum.UNKNOW_ERROR);
        }
    }

}
