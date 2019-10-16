package com.yinglishzhi.agenttest;

/**
 * @author LDZ
 * @date 2019-10-16 10:21
 */
public class VMTest {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(10000);
            new Thread(new WaitThread()).start();
        }
    }

    static class WaitThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(11);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
