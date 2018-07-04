# 基础知识
## 1.概述
### 1.线程简介
#### 1.什么是线程

#### 2.并发编程的优点
-  更多的处理器核心

  线程是大多数操作系统调度的基本单元，一个程序作为一个进程来运行，程序运行过程中能够创建多个线程，而一个线程一个时刻只能运行在一个处理器核心上。使用多线程技术，将计算逻辑分配到多个处理器核心上，就会减少程序的处理时间，并且随着多处理器核心的加入而变得更加有效率。
-  更快的响应式时间

  将数据不一致的操作派发到其他线程中，缩短了响应时间，提升用户体验。
-  更好的编程模型

  java为多线程编程提供了良好、考究且一致的编程模型，使开发人员更加专注于问题的解决，为所遇到的问题建立适合的模型，而不是想办法考虑如何将其多线程化。
#### 3.并发编程的缺点
  **多线程技术有这么多的好处，难道就没有一点缺点么，就在任何场景下就一定适用么？很显然不是。**
-  频繁的上下文切换

  时间片是CPU分配给各个线程的时间，因为时间非常短，所以CPU不断通过切换线程，让我们觉得多个线程是同时执行的，时间片一般是几十毫秒。而每次切换时，需要保存当前的状态起来，以便能够进行恢复先前状态，而这个切换时非常损耗性能，过于频繁反而无法发挥出多线程编程的优势。通常减少上下文切换可以采用无锁并发编程、CAS算法，使用最少的线程和使用协程。</br>
  <font color=gray>无锁并发编程：可以参照concurrentHashMap锁分段的思想，不同的线程处理不同段的数据，这样在多线程竞争的条件下，可以减少上下文切换的时间。</font>
  <font color=gray>CAS算法，利用Atomic下使用CAS算法来更新数据，使用了乐观锁，可以有效的减少一部分不必要的锁竞争带来的上下文切换</font><br>
  使用最少线程：避免创建不需要的线程，比如任务很少，但是创建了很多的线程，这样会造成大量的线程都处于等待状态<br>
  协程：在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换
-  线程安全

  多线程编程中最难以把握的就是临界区线程安全问题，稍微不注意就会出现死锁的情况，一旦产生死锁就会造成系统功能不可用。
```Java
public class DeadLockDemo {
  private static String resource_a = "A";
  private static String resource_b = "B";

  public static void main(String[] args) {
      deadLock();
  }

  public static void deadLock() {
      Thread threadA = new Thread(new Runnable() {
          @Override
          public void run() {
              synchronized (resource_a) {
                  System.out.println("get resource a");
                  try {
                      Thread.sleep(3000);
                      synchronized (resource_b) {
                          System.out.println("get resource b");
                      }
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
      });
      Thread threadB = new Thread(new Runnable() {
          @Override
          public void run() {
              synchronized (resource_b) {
                  System.out.println("get resource b");
                  synchronized (resource_a) {
                      System.out.println("get resource a");
                  }
              }
          }
      });
      threadA.start();
      threadB.start();

  }
}
```

在上面的这个demo中，开启了两个线程threadA、threadB，其中threadA占用了resource_a, 并等待被threadB释放的resource_b。threadB占用了resource_b正在等待被threadA释放的resource_a。因此threadA、threadB出现线程安全的问题，形成死锁。同样可以通过jps、jstack证明这种推论：
```
"Thread-1":
waiting to lock monitor 0x000000000b695360 (object 0x00000007d5ff53a8, a java.lang.String),
which is held by "Thread-0"
"Thread-0":
waiting to lock monitor 0x000000000b697c10 (object 0x00000007d5ff53d8, a java.lang.String),
which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
      at learn.DeadLockDemo$2.run(DeadLockDemo.java:34)
      - waiting to lock <0x00000007d5ff53a8(a java.lang.String)
      - locked <0x00000007d5ff53d8(a java.lang.String)
      at java.lang.Thread.run(Thread.java:722)
"Thread-0":
      at learn.DeadLockDemo$1.run(DeadLockDemo.java:20)
      - waiting to lock <0x00000007d5ff53d8(a java.lang.String)
      - locked <0x00000007d5ff53a8(a java.lang.String)
      at java.lang.Thread.run(Thread.java:722)

Found 1 deadlock.
```

如上所述，完全可以看出当前<font color=red>死锁</font>的情况，那么，通常可以用如下方式避免死锁的情况：
1. 避免一个线程同时获得多个锁；
2. 避免一个线程在锁内部占有多个资源，尽量保证每个锁只占用一个资源；
3. 尝试使用定时锁，使用lock.tryLock(timeOut)，当超时等待时当前线程不会阻塞；
4. 对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况<br>
<br>**结论**<br>

如何正确的使用多线程编程技术有很大的学问。比如如何保证线程安全、如何正确理解由于JMM内存模型在原子性,有序性,可见性带来的问题，比如数据脏读，DCL等这些问题。贼牛逼。牛逼。屌。

#### 4.线程的优先级
  现在操作系统基本采用时分的形式调度运行的线程，操作系统会分出一个个时间片，线程会分配到若干时间片，当线程的时间片用完了就会发生线程调度，并等待下次分配。线程分配到的时间片多少也就决定了线程使用处理器资源的多少，而线程优先级就是决定线程需要多或者少分配一些处理器资源的线程属性。<br>
  在java线程中，通过一个整型成员变量priority来控制优先级，优先级的范围从1～10，在线程构建的时候可以通过<font color=green>*setPriority()*</font> 方法来修改优先级，默认优先级是5，优先级高的线程分配时间片的数量要多于优先级的线程。设置优先级时，针对频繁阻塞（休眠或者I/O操作）的线程需要设置较高优先级，而偏重计算（需要较多CPU时间或者偏运算）的线程则设置较低的优先级，确保处理器不会被独占。在不同的JVM以及操作系统上，线程规划会存在差异，有些操作系统甚至会忽略对线程优先级的设定。
## 2.线程的状态和基本操作
### 1.如何创建新线程
  一个java程序从main()方法开始执行，然后按照既定的代码逻辑执行，看似没有其他线程参与，但实际上java程序天生就是一个多线程程序，包含了：<br>
  （1）分发处理发送给给JVM信号的线程；<br>
  （2）调用对象的finalize方法的线程；<br>
  （3）清除Reference的线程；<br>
  （4）main线程，用户程序的入口。<br>
  那么，如何在用户程序中新建一个线程了，只要有三种方式：

-  通过继承Thread类，重写run方法；

-  通过实现runable接口；

-  通过实现callable接口这三种方式，下面看具体demo。
```Java
public class CreateThreadDemo {

     public static void main(String[] args) {
         //1.继承Thread
         Thread thread = new Thread() {
             @Override
             public void run() {
                 System.out.println("继承Thread");
                 super.run();
             }
         };
         thread.start();
         //2.实现runable接口
         Runnable r = ()-{
           System.out.println("实现runable接口");
         };
         Thread thread1 = new Thread(r);
         thread1.start();
         //3.实现callable接口
         ExecutorService service = Executors.newSingleThreadExecutor();
         Future<String> future = service.submit(new Callable() {
             @Override
             public String call() throws Exception {
                 return "通过实现Callable接口";
             }
         });
         try {
             String result = future.get();
             System.out.println(result);
         } catch (InterruptedException e) {
             e.printStackTrace();
         } catch (ExecutionException e) {
             e.printStackTrace();
         }
     }

 }
```
### 2.线程状态
  ![线程状态转化图](https://github.com/YinglishZhi/struggle-object/blob/master/img/2.2_1线程状态图.jpeg)
### 3.线程的基本操作
### 4.守护线程
# 并发理论
## 1.JMM内存模型
## 2.重排序
## 3.三大性质
# 并发关键字
## 1.synchronized
## 2.volatile
## 3.final
# lock体系
## 1.lock与synchronize
## 2.AQS
## 3.AQS源码解析
## 4.ReentrantLock
## 5.ReentrantReadWriteLock
## 6.Condition机制
## 7.LockSupport
# 并发容器
## 1.concurrentHashMap
## 2.
