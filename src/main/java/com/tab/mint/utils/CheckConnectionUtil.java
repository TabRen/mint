package com.tab.mint.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 检查ip 端口是否可以连接
 * <p>
 * Created by tab on 5/8/17.
 */
@Component
public class CheckConnectionUtil {

    public boolean checkConnection(String ip, int port) {

        Socket socket = new Socket();

        try {
            socket.connect(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
