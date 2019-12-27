package com.YinglishZhi;

/**
 * @author LDZ
 * @date 2019-12-18 21:13
 */
@FunctionalInterface
public interface FI {
    // 正确的函数式接口

    // 抽象方法
    void sub();

    // java.lang.Object中的public方法
    public boolean equals(Object var1);

    // 默认方法
    public default void defaultMethod() {

    }

    // 静态方法
    public static void staticMethod() {
        System.out.println("hello");
    }
}



