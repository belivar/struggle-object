package com.YinglishZhi.io.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Nio
 *
 * @author LDZ
 * @date 2019-08-11 15:08
 */
public class NioSocketServer {


    public void fun1(){

    }

    public void fun1(int a){

    }

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(BioSocketServer.DEFAULT_PORT));

            Selector selector = Selector.open();

            // 服务器通道只能注册 SelectionKey.OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (selector.select(100) == 0) {
                    System.out.println("100ms仍未发现事件");
                    continue;
                }
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {
                    SelectionKey readKey = selectionKeys.next();
                    selectionKeys.remove();
                    SelectableChannel selectableChannel = readKey.channel();

                    if (readKey.isValid() && readKey.isAcceptable()) {
                        System.out.println("=======channel 通道已经就绪 =======");
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectableChannel;
                        SocketChannel socketChannel = serverChannel.accept();
                    } else if (readKey.isValid() && readKey.isConnectable()) {
                        System.out.println("======= socket channel 建立链接======");
                    } else if (readKey.isValid() && readKey.isReadable()) {
                        System.out.println("======= socket channel 数据准备完成 可以去读取======");

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
