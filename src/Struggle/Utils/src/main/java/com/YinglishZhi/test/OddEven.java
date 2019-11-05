package com.YinglishZhi.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LDZ
 * @date 2019-09-30 14:19
 */
public class OddEven {
    // 奇数
    private synchronized void printOdd() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + " ===== " + i);
                this.notify();
                // 打印一个数 卡一个数
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 偶数
    private synchronized void printEven() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " ===== " + i);
                this.notify();
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {


        final OddEven s = new OddEven();
        // 偶数线程
        Thread threadEven = new Thread(s::printEven, "even");
        // 奇数线程
        Thread threadOdd = new Thread(s::printOdd, "odd");

        threadEven.start();
        threadOdd.start();

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(threadOdd);
//        executorService.execute(threadEven);
//        executorService.shutdown();
//        threadOdd.start();
//        threadEven.start();

    }

}
