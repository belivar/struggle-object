package producerconsumer.lock;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LDZ
 * @date 2019-08-19 23:40
 */
public class Main {


    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        int maxLength = 8;

        ReentrantLock lock = new ReentrantLock();

        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();

        Produce p = new Produce(list, maxLength, lock, empty);
        Consumer c = new Consumer(list, full, lock);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new ProductThread(p));
        }
        for (int i = 0; i < 10; i++) {
            executorService.submit(new ConsumerThread(c));
        }
    }

    static class ConsumerThread extends Thread {
        Consumer c;

        ConsumerThread(Consumer c) {
            this.c = c;
        }

        @Override
        public void run() {
            while (true) {
                c.consumer();
            }
        }
    }

    static class ProductThread extends Thread {
        Produce p;

        ProductThread(Produce p) {
            this.p = p;
        }

        @Override
        public void run() {
            while (true) {
                p.produce();
            }
        }
    }
}
