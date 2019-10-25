package com.yinglishzhi.test;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author LDZ
 * @date 2019-10-15 17:33
 */
@Slf4j
public class TransClass {
    int getNumber() {
        return 1;
    }
}
