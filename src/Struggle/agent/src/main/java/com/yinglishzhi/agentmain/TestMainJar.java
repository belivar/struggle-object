package com.yinglishzhi.agentmain;

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
            int number = new TransClass().getNumber();
            System.out.println(number);
            if (3 == number || count >= 1000) {
                break;
            }
        }
    }
}
