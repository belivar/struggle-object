package com.yinglishzhi;

import com.yinglishzhi.test.TransClass;
import com.yinglishzhi.test.Transformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

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
        Class[] allLoadedClasses = inst.getProperty1llLoadedClasses();
        System.out.println(allLoadedClasses.length + "哈哈");
    }

    public void test() {
        System.out.println("执行 test 方法");
        Instrumentation inst = spyCore.instrumentation;
        System.out.println("=========");
        inst.addTransformer(new Transformer());
        try {
            inst.retransformClasses(TransClass.class);
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
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
