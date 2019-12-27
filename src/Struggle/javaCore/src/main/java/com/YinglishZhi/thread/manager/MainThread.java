package com.YinglishZhi.thread.manager;

/**
 * @author LDZ
 * @date 2019-12-09 11:13
 */
public class MainThread {

    public static void main(String[] args) {
        main();
    }


    public static int main() {
        System.out.println(1111);


        Runnable runnable = () -> {
            try {
                System.out.println("wait 5000ms");
                Thread.sleep(1000);

                System.out.println("wait 5000ms ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(runnable).start();

        System.out.println(12222);

        return 1;
    }
}
