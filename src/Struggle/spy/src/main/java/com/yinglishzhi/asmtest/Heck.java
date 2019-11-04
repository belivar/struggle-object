package com.yinglishzhi.asmtest;

import java.lang.reflect.Method;

/**
 * @author LDZ
 * @date 2019-10-31 17:41
 */
public class Heck {

    public static volatile Method TEST_METHOD;

    public static void init(Method testMethod) {
        TEST_METHOD = testMethod;
    }
}
