package producerconsumer.waitnotify;

import java.util.ArrayList;
import java.util.Random;

/**
 * producer
 *
 * @author LDZ
 * @date 2019-08-19 22:43
 */
public class Produce{

    private final ArrayList<Integer> list;

    private int maxLength;

    Produce(int maxLength, ArrayList<Integer> list) {
        this.maxLength = maxLength;
        this.list = list;
    }

    void produce() {
        synchronized (list) {
            try {
                while (list.size() == maxLength) {
                    System.out.println("生产者" + Thread.currentThread().getName() + "waiting");
                    list.wait();
                }
                int value = new Random().nextInt();
                list.add(value);
                System.out.println("生产者" + Thread.currentThread().getName() + "生产数据" + value);
                list.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
