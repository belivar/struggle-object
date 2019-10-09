package com.YinglishZhi.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

/**
 * @author LDZ
 * @date 2019-09-20 10:58
 */
public class SocketTest {


    public static void main(String[] args) throws SocketException {
        try {
            HashMap<Object, Object> map = new HashMap<>();
            map.put(null, null);

            System.out.println(map.get(null));
//            Socket socket;
//            socket = new Socket();
//            socket.setSoTimeout(10000);
//            socket.setKeepAlive(true);
//            socket.connect(new InetSocketAddress("192.168.14.138", 3837), 10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
