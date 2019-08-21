package producerconsumer.waitnotify;

import java.util.ArrayList;

/**
 * consumer
 *
 * @author LDZ
 * @date 2019-08-19 22:47
 */
public class Consumer {

    public ArrayList<Integer> list;

    public Consumer(ArrayList<Integer> list) {
        this.list = list;
    }

    public void consumer() {
        synchronized (list) {
            try {
                while (list.isEmpty()) {
                    System.out.println("消费者" + Thread.currentThread().getName() + "waiting");
                    list.wait();
                }
                int value = list.remove(0);
                System.out.println("消费者" + Thread.currentThread().getName() + "消费数据" + value);
                list.notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
