package com.YinglishZhi.asm.agent;

import com.YinglishZhi.asm.Demo;
import com.YinglishZhi.asm.MyClassLoader;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author LDZ
 * @date 2019-10-15 17:33
 */
public class TransClass {
    public int getNumber() {
        return 1;
    }


    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, MalformedURLException {

        String JAR_PATH = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/out/artifacts/Utils_jar/Utils.jar";
        File agentJarFile = new File(JAR_PATH);
        ClassLoader c = new MyClassLoader(new URL[]{agentJarFile.toURI().toURL()});
        final Class<?> server = c.loadClass("com.yinglishzhi.asm.Demo");
        Object instance = server.getMethod("getInstance", Instrumentation.class).invoke(null, null);

        server.getMethod("test").invoke(instance);

    }
}
