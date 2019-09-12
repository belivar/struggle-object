package producerconsumer.waitnotify;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LDZ
 * @date 2019-08-19 22:50
 */
public class Main {


    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(15);
        ProduceThread pt = new ProduceThread(new Produce(8, list));
        ConsumerThread ct = new ConsumerThread(new Consumer(list));
        for (int i = 0; i < 5; i++) {
            executorService.submit(pt);
        }

        for (int i = 0; i < 8; i++) {
            executorService.submit(ct);
        }

    }

    static class ProduceThread extends Thread {
        private Produce p;

        ProduceThread(Produce p) {
            this.p = p;
        }

        @Override
        public void run() {
            while (true) {
                p.produce();
            }
        }
    }

    static class ConsumerThread extends Thread {
        private Consumer c;

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
}
