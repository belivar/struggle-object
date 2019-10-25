package com.YinglishZhi.netty;

/**
 * telnet
 *
 * @author LDZ
 * @date 2019-10-22 10:53
 */
public class telnet {
    public static void main(String[] args) {
        telnet t = new telnet();
        t.test();
    }


    public void test() {
        NettyTelnetServer nettyTelnetServer = new NettyTelnetServer();
        try {
            nettyTelnetServer.open();
        } catch (InterruptedException e) {
            nettyTelnetServer.close();
        }
    }
}
