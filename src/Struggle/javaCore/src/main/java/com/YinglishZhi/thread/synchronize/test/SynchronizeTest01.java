package com.YinglishZhi.thread.synchronize.test;

import java.util.concurrent.TimeUnit;

/**
 * @author LDZ
 * @date 2019-08-11 22:27
 */
public class SynchronizeTest01 {

    public static void main(String[] args) {
        final ASyncHotProductService aSyncHotProductService = new ASyncHotProductService();
        new Thread(aSyncHotProductService::serviceMethodA).start();
        new Thread(aSyncHotProductService::serviceMethodB).start();
        new Thread(aSyncHotProductService::serviceMethodC).start();
    }


    static class ASyncHotProductService {
        //测试业务A
        void serviceMethodA() {
            try {

                System.out.println("HotProductService : A begin time=" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(5);
                System.out.println("HotProductService : A end   time=" + System.currentTimeMillis());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //测试业务B
        void serviceMethodB() {

            System.out.println("HotProductService : B begin time=" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HotProductService : B end   time=" + System.currentTimeMillis());

        }

        //测试业务C
        void serviceMethodC() {

            System.out.println("HotProductService : C begin time=" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HotProductService : C end   time=" + System.currentTimeMillis());

        }
    }
}
