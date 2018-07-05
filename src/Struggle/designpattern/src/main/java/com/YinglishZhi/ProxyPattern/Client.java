package com.YinglishZhi.ProxyPattern;

import com.YinglishZhi.ProxyPattern.Proxy.Proxy;

public class Client {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}
