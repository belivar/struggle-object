package producerconsumer.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author LDZ
 * @date 2019-08-19 23:37
 */
public class Consumer {

    private ArrayList<Integer> list;

    private Condition condition;

    private Lock lock;

    public Consumer(ArrayList<Integer> list, Condition condition, Lock lock) {
        this.list = list;
        this.condition = condition;
        this.lock = lock;
    }

    public void consumer() {
        lock.lock();
        try {
            while (list.isEmpty()) {
                System.out.println("消费者" + Thread.currentThread().getName() + "waiting");
                condition.await();
            }
            int i = list.remove(0);
            System.out.println("消费者" + Thread.currentThread().getName() + "消费数据：" + i);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
