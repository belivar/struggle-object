package com.YinglishZhi.io.test.chatroom.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author LDZ
 * @date 2019-11-06 10:20
 */
public class Server implements Runnable {

    private ServerSocketChannel serverSocketChannel = null;

    // 客户端的连接
    private List<SocketChannel> socketChannelList = null;

    private Selector selector = null;

    private boolean stop = false;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Server(int port) {

        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            this.socketChannelList = new ArrayList<>();

            System.out.println("server is listening now ......");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (!stop) {

            int num = 0;
            try {
                num = selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (num > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        // acceptable
                    } else if (key.isReadable()) {
                        // readable
                    }
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("server is stop");

        if (this.serverSocketChannel != null && this.serverSocketChannel.isOpen()) {
            try {
                this.serverSocketChannel.close();
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
