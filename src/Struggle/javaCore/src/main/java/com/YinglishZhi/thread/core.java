package com.YinglishZhi.thread;

/**
 * @author LDZ
 * @date 2019-11-03 11:58
 */
public class core {
    public static void main(String[] args) {
//        LiftOff liftOff = new LiftOff();
//        Thread t = new Thread(liftOff);
//
//        t.start();

        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("waiting for LiftOff");
    }

    static class LiftOff implements Runnable {

        protected int countDown = 10;

        private static int taskCount = 0;

        private final int id = taskCount++;

        public LiftOff() {

        }

        public LiftOff(int countDown) {
            this.countDown = countDown;
        }

        public String status() {
            return "#" + id + "(" + (countDown > 0 ? countDown : "liftOff") + ")";
        }

        @Override
        public void run() {
            while (countDown-- > 0) {
                System.out.print(status());
//                Thread.yield();
            }
        }
    }
}
