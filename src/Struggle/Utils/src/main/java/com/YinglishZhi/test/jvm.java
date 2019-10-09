package com.YinglishZhi.test;

/**
 * @author LDZ
 * @date 2019-10-08 10:57
 */
public class jvm {

    public int a = 3;

    static Integer si = 6;

    String s = "Hello World";

    public static void main(String[] args) {
        jvm jvm = new jvm();

        jvm.a = 8;
        si = 9;
    }

    private void jvm() {
        this.a = a;
    }

    private static int add(int a, int b) {
        return a + b;
    }
}
