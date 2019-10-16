package com.yinglishzhi.agenttest;

import java.lang.instrument.Instrumentation;

/**
 * @author LDZ
 * @date 2019-10-16 10:24
 */
public class MyAgentMain {
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("MyAgentMain agentmain attach...");

        System.getProperties().setProperty("monitor.conf", args);

        for (Class clazz : inst.getAllLoadedClasses()) {
            System.out.println(clazz.getName());
        }

        System.out.println("MyAgentMain agentmain end...");
    }

}
