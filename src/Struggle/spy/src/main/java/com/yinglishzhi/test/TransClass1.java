package com.yinglishzhi.test;

/**
 * @author LDZ
 * @date 2019-10-15 17:33
 */
public class TransClass1 {

    private static TransClass1 transClass;

    public static TransClass1 getInstance() {
        if (null == transClass) {
            synchronized (TransClass1.class) {
                if (null == transClass) {
                    transClass = new TransClass1();
                }
            }
        }
        return transClass;
    }


    public int getNumber() {
        return 3;
    }
}
