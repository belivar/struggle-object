package com.YinglishZhi.thread.synchronize.test;

/**
 * @author LDZ
 * @date 2019-08-11 22:27
 */
public class SynchronizeTest03 {

    public static void main(String[] args) {
        ASyncHotProductService aSyncHotProductService = new ASyncHotProductService();
        new Thread(() -> aSyncHotProductService.serviceMethod("AAAAA")).start();
        new Thread(() -> aSyncHotProductService.serviceMethod("BBBBB")).start();
        new Thread(() -> aSyncHotProductService.serviceMethod("CCCCC")).start();
    }


    static class ASyncHotProductService {
        private String name;

        //测试业务C
        void serviceMethod(String name) {
            String lock = new String();
            synchronized (lock) {
                System.out.println("HotProductService,thread name=" + Thread.currentThread().getName()
                        + " 进入代码快:" + System.currentTimeMillis());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("HotProductService,thread name=" + Thread.currentThread().getName()
                        + " 进入代码快:" + System.currentTimeMillis() + "入参uname:" + name);
            }
        }
    }
}
