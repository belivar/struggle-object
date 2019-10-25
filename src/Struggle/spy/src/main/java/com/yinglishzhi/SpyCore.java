package com.yinglishzhi;

import java.lang.instrument.Instrumentation;

/**
 * @author LDZ
 * @date 2019-10-25 17:56
 */
public class SpyCore {

    private Instrumentation instrumentation;
    private static volatile SpyCore spyCore;


    private SpyCore(Instrumentation inst) {
        this.instrumentation = inst;
        printClasses(inst);
    }

    private static void printClasses(Instrumentation inst) {
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class clazz : allLoadedClasses) {
            System.out.println(clazz.getName());
        }
    }

    public void test() {
        Class[] classes = spyCore.instrumentation.getAllLoadedClasses();
        System.out.println("总共这些 {} 类" + classes.length);
    }

    public static SpyCore getInstance(Instrumentation instrumentation) {
        if (null == spyCore) {
            synchronized (SpyCore.class) {
                if (null == spyCore) {
                    spyCore = new SpyCore(instrumentation);
                }
            }
        }
        return spyCore;
    }
}
