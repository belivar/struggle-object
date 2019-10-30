package com.YinglishZhi.asm.agent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * @author LDZ
 * @date 2019-10-15 17:35
 */
public class TestMainJar {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new TransClass().getNumber());
        int count = 0;
        while (true) {
            Thread.sleep(500);
            count++;
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            try {
                Class clazz = classLoader.loadClass("com.YinglishZhi.asm.agent.TransClass");
                Method method = clazz.getMethod("getNumber");
                int i = (int) method.invoke(new TransClass());
                System.out.println("====" + i);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            int number = new TransClass().getNumber();
//            System.out.println(number);
            if (3 == number || count >= 1000) {
                break;
            }
        }
    }
}
