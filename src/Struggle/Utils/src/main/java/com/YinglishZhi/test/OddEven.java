package com.YinglishZhi.test;

/**
 * @author LDZ
 * @date 2019-09-30 14:19
 */
public class OddEven {
    // 奇数
    private synchronized void printOdd() {
        for(int i = 0; i <= 100; i++){
            if (i % 2 == 1){
                System.out.println(Thread.currentThread().getName() + "" + i);
                this.notify();
                // 打印一个数 卡一个数
                try{
                    this.wait();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    // 偶数
    private synchronized void printEven() {
        for(int i = 0; i <= 100; i++){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + "" + i);
                this.notify();
                try{
                    this.wait();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){


       final OddEven s = new OddEven();
        // 奇数线程
        Thread thread1 = new Thread(s::printEven, "even");
        // 偶数线程
        Thread thread2 = new Thread(s::printOdd, "odd");

        thread1.start();
        thread2.start();

    }

}
