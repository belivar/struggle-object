package com.YinglishZhi.io.test.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


public class NIOServer implements Runnable {
    /**
     * 选择器
     */
    private Selector selector;

    /**
     * 通道
     */
    ServerSocketChannel serverSocketChannel;

    public NIOServer(int port) throws IOException {
        // 1. 打开 ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 2. 绑定监听地址 InetSocketAddress
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", port));
        // 3. 创建 selector 启动线程
        selector = Selector.open();
        // 4. 在通道上注册 selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("The time server is start in port " + port);
    }

    @Override
    public void run() {
        System.out.println("server started succeed!");

        try {
            while (true) {
                selector.select();
                Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
                while (ite.hasNext()) {
                    SelectionKey key = ite.next();
                    ite.remove();

                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accept(SelectionKey key) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("is acceptable");
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void recvAndReply(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int i = channel.read(buffer);
        if (i != -1) {
            String msg = new String(buffer.array()).trim();
            System.out.println("NIO server received message =  " + msg);
            System.out.println("NIO server reply =  " + msg);
            channel.write(ByteBuffer.wrap(msg.getBytes()));
        } else {
            channel.close();
        }
    }

    public void read(SelectionKey selectionKey) {
        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            int len = channel.read(byteBuffer);
            if (len > 0) {

                byteBuffer.flip();
                byte[] byteArray = new byte[byteBuffer.limit()];
                byteBuffer.get(byteArray);
                System.out.println("NioSocketServer receive from client:" + new String(byteArray));

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            try {
                serverSocketChannel.close();
                selectionKey.cancel();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer(8080);
        new Thread(server).start();
    }
}
