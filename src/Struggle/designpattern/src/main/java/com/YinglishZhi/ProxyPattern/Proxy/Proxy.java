package com.YinglishZhi.ProxyPattern.Proxy;

import com.YinglishZhi.ProxyPattern.Subject.Impl.RealSubject;
import com.YinglishZhi.ProxyPattern.Subject.Isubject;

public class Proxy implements Isubject {

    RealSubject realSubject;

    public void request() {
            if (realSubject == null){
                realSubject = new RealSubject();
            }
            before();
            realSubject.request();
            after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}
