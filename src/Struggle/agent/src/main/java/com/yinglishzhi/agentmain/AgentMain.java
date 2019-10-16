package com.yinglishzhi.agentmain;

import java.lang.instrument.Instrumentation;

/**
 * @author LDZ
 * @date 2019-10-15 17:32
 */
public class AgentMain {
    // 在java程序启动后附加 agent
    // vm.loadAgent(jar);
    // 必须在jar包的 manifest文件中指定 Agent-Class 为当前类
    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {

        System.out.println("执行了agentmain 方法 ");

        System.out.println("调用堆栈信息");
        StackTraceElement[] se = Thread.currentThread().getStackTrace();
        for (int i = 0; i < se.length; i++) {
            StackTraceElement stackTraceElement = se[i];
            System.out.println(stackTraceElement.toString());
        }

        System.out.println("#########################");

        inst.addTransformer(new Transformer(), true);

        System.out.println("重新加载类：" + TransClass.class);
        inst.retransformClasses(TransClass.class);
        System.out.println("Agent Main Done");
    }

    // 启动参数的方式执行的是这个方法
    // java -javaagent:xxxx-agrent.jar
    // 必须在 jar包的 manifest文件中指定 Premain-Class 为当前类
    public static void premain(String agentArgs, Instrumentation inst) throws Exception {

        System.out.println("执行了premain 方法 ");

        System.out.println("调用堆栈信息");
        StackTraceElement[] se = Thread.currentThread().getStackTrace();
        for (int i = 0; i < se.length; i++) {
            StackTraceElement stackTraceElement = se[i];
            System.out.println(stackTraceElement.toString());
        }

        System.out.println("#########################");

        inst.addTransformer(new Transformer());

        System.out.println("Agent Main Done");
    }
}
