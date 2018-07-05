package com.YinglishZhi.ProxyPattern.Subject.Impl;

import com.YinglishZhi.ProxyPattern.Subject.Isubject;

public class RealSubject implements Isubject {
    public void request() {
        System.out.println("真实的请求");
    }
}
