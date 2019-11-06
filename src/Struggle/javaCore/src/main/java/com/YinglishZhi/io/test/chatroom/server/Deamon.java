package com.YinglishZhi.io.test.chatroom.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author LDZ
 * @date 2019-11-05 21:04
 */
public class Deamon implements Runnable {

    private boolean flag = true;


    private ServerSocketChannel serverChannel = null;
    private Selector selector = null;
    /**
     * 记录进来的所有的客户端连接
     */
    private List<SocketChannel> clientChannels = null;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Deamon(int port) {
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(port));
            selector = Selector.open();
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            this.clientChannels = new ArrayList<>();
            System.out.println("Server is listening now...");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
//        System.out.println("server listening..");
        while (this.flag) {
            int num = 0;
            try {
                //此处select()阻塞了线程
                num = selector.select();
            } catch (IOException e) {
                System.out.println("Error while select channel:" + e);
            }
            if (num > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        // 监听到有新的连接则再注册读操作
                        this.clientChannels.add(Dealer.accept(selector,
                                serverChannel));
                    } else if (key.isReadable()) {
                        // 监听到读操作
                        try {
                            Dealer.read(selector, key, clientChannels);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("server to close..");
        if (this.serverChannel != null && this.serverChannel.isOpen()) {
            try {
                this.serverChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.selector != null && this.selector.isOpen()) {
            try {
                this.selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
