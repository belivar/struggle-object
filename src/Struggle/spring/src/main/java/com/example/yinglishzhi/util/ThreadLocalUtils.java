package com.example.yinglishzhi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author LDZ
 * @date 2019-09-23 14:24
 */
public class ThreadLocalUtils {

    private static ThreadLocal<ArrayList<String>> threadLocal = new ThreadLocal<>();


    public static void setThreadLocal(String ss) {
        ArrayList<String> result = threadLocal.get();

        if (null == result || 0 == result.size()) {
            ArrayList<String> objects = new ArrayList<>();
            objects.add(ss);
            threadLocal.set(objects);
        } else {
            result.add(ss);
            threadLocal.set(result);
        }
    }

    public static List<String> getThreadLocal() {
        System.out.println(Thread.currentThread().getName());
        return threadLocal.get();
    }
}
