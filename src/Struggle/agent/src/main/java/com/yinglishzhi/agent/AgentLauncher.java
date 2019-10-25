package com.yinglishzhi.agent;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author LDZ
 * @date 2019-10-25 16:10
 */
@Slf4j
public class AgentLauncher {

    private static volatile ClassLoader agentClassLoader;

    public static void premain(String args, Instrumentation inst) {
        log.info("执行了 premain 方法......");
        main(args, inst);
    }

    public static void agentmain(String args, Instrumentation inst) {
        log.info("执行了 agentmain 方法......");
        main(args, inst);
    }


    private static synchronized void main(String args, final Instrumentation inst) {
        try {

            ClassLoader agentClassLoader = loadOrDefineClassLoader(args);
            final Class<?> server = agentClassLoader.loadClass("com.yinglishzhi.SpyCore");
            Object instance = server.getMethod("getInstance", Instrumentation.class).invoke(null, inst);
            server.getMethod("test").invoke(instance);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static ClassLoader loadOrDefineClassLoader(String agentJar) throws MalformedURLException {
        if (null == agentClassLoader) {
            agentClassLoader = new AgentClassLoader(new URL[]{new File(agentJar).toURI().toURL()});
        }
        return agentClassLoader;
    }

}
