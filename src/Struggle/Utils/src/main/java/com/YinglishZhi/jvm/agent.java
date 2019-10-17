package com.YinglishZhi.jvm;

import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;

/**
 * @author LDZ
 * @date 2019-10-15 16:19
 */
@Slf4j
public class agent {


    public static void premain(String agentOps, Instrumentation instrumentation) {
        System.out.println("=====premain======");
        System.out.println(agentOps);
    }

    public static void premain(String agentOps) {
        System.out.println("====premain方法执行2====");
        System.out.println(agentOps);
    }

}
