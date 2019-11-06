package com.YinglishZhi.io.test.chatroom.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author LDZ
 * @date 2019-11-06 10:12
 */
public class Deamon implements Runnable{
    /**
     * 选择器，用于监听注册在上面的SocketChannel的状态
     */
    private Selector selector = null;

    /**
     * SocketChannel 用户发送和接受数据的信道
     */
    private SocketChannel channel = null;

    /**
     * 运行标识。在线程里此标识为false的时候会推出线程
     * 该属性在ExitCommandListener里通过调用setFlag方法修改，用于通知线程用户要求退出的程序
     */
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Deamon(String address, int port) {
        try {
            channel = SocketChannel.open(new InetSocketAddress(address, port));
            channel.configureBlocking(false);
            selector = Selector.open();
            // 客户端直接注册读和写操作
            channel.register(selector, SelectionKey.OP_READ
                    | SelectionKey.OP_WRITE);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void chancelToWrite(ByteBuffer buffer){
        try {
            channel.write(buffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("client run..");
        while (this.flag) {
            /**
             * 如果可以继续执行，则在循环体内循环执行监听选择操作
             */
            int num = 0;
            try {
                /**
                 * 得到处于可读或者可写状态的SocketChannel对象的个数
                 */
                // 客户端的select()并不阻塞线程,是因为客户端一启动就是SelectionKey.OP_WRITE状态
//                 System.out.println("client select..");
                num = this.selector.select();

//                 System.out.println("client num:"+num);
            } catch (IOException e) {
                /**
                 * 如果出现异常，则此处应该加上日志打印，然后跳出循环,执行循环体下面的释放资源操作
                 */
                break;
            }

            if (num > 0) {
                /**
                 * 如果有多个SocketChannel处于可读或者可写状态，则轮询注册在Selector上面的SelectionKey
                 */
                Iterator<SelectionKey> keys = selector.selectedKeys()
                        .iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    /**
                     * 此步操作用于删除该SelectionKey的被选中状态
                     */
                    keys.remove();
                    if (key.isReadable()) {
                        System.out.println("client isReadable..");
                        /**
                         * 如果是读操作，则调用读操作的处理逻辑
                         */
                        try {
                            Dealer.read((SocketChannel) key.channel());
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else if (key.isWritable()) {
                        //客户端的写状态是一直就绪的
                        // System.out.println("client isWritable..");
                        /**
                         * 如果是写操作，则调用写操作的处理逻辑
                         */
//                        Dealer.write((SocketChannel) key.channel());
                    }
                }
            }

            /*取消关注，多用在多线程的时候
             * key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
             *
             * 增加关注
             * key.interestOps(key.interestOps() | SelectionKey.OP_READ);
             * */

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (this.channel != null && this.channel.isOpen()) {
            /**
             * 关闭SocketChannel
             */
            try {
                this.channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.selector != null && this.selector.isOpen()) {
            /**
             * 关闭Selector选择器
             */
            try {
                this.selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
