package producerconsumer.lock;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * produce
 *
 * @author LDZ
 * @date 2019-08-19 23:29
 */
class Produce {

    private ArrayList<Integer> list;

    private Integer maxLength;

    private Lock lock;

    private Condition condition;

    Produce(ArrayList<Integer> list, Integer maxLength, Lock lock, Condition condition) {
        this.list = list;
        this.maxLength = maxLength;
        this.lock = lock;
        this.condition = condition;
    }

    void produce() {
        lock.lock();
        try {
            while (list.size() == maxLength) {
                System.out.println("生产者" + Thread.currentThread().getName() + "waiting");
                condition.await();
            }
            int i = new Random().nextInt();
            list.add(i);
            System.out.println("生产者" + Thread.currentThread().getName() + "生产数据：" + i);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
