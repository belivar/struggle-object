package com.YinglishZhi.asm;

import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;

/**
 * @author LDZ
 * @date 2019-10-23 11:21
 */
@Slf4j
public class Demo {
    private Instrumentation instrumentation;

    private Demo(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
        printClasses(instrumentation);
    }

    private static void printClasses(Instrumentation inst) {
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class clazz : allLoadedClasses) {
            log.info("class = {}", clazz.getName());
        }
    }

    private static volatile Demo demo;

    public void test() {
        Instrumentation instrumentation = demo.instrumentation;
        int length = instrumentation.getAllLoadedClasses().length;
        System.out.println(length);
    }


    /**
     * 单例
     *
     * @return Demo
     */
    public static Demo getInstance(Instrumentation inst) {
        if (null == demo) {
            synchronized (Demo.class) {
                if (null == demo) {
                    demo = new Demo(inst);
                }
            }
        }
        return demo;
    }
}
