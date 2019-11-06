package com.YinglishZhi.io.test.chatroom.server;

/**
 * @author LDZ
 * @date 2019-11-06 10:18
 */
public class ServerBoot {


    public static void main(String[] args) {
        new Thread(new Deamon(9999)).start();
    }
}
