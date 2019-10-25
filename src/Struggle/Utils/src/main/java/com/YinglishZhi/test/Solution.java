package com.YinglishZhi.test;

import java.util.concurrent.Callable;

/**
 * @author LDZ
 * @date 2019-07-31 11:46
 */
public class Solution {

    public static void main(String[] args) {
//        AddField s = new AddField();
//
//        new Thread(s::showService1).start();
//        new Thread(s::showService2).start();
//
//        Callable<Integer> callable = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                System.out.println(111);
//                return 11;
//            }
//        };
//
//        FutureTask<Integer> future = new FutureTask<>(callable);
//
//        Thread thread = new Thread(future);
//
//        thread.start();

       Thread t = new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println(11);
           }
       });

       t.start();
    }


    class MyThread<V> implements Callable<V> {

        @Override
        public V call() throws Exception {
            return null;
        }
    }


    private void showService1() {
        try {
            synchronized (Solution.class) {
                System.out.println("HotProductService" + "A begin time=" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("HotProductService" + "A end   time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showService2() {
        synchronized (Solution.class) {
            System.out.println("HotProductService" + "B begin time=" + System.currentTimeMillis());
            System.out.println("HotProductService" + "B end   time=" + System.currentTimeMillis());

        }
    }


}
