package com.YinglishZhi.io.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LDZ
 * @date 2019-09-29 16:23
 */
public class MultiplexerTimeServerHandler implements Runnable {

    /**
     * 选择器
     */
    private Selector selector;

    /**
     * 初始化多路复用器 绑定监听端口
     *
     * @param port 端口
     */
    private MultiplexerTimeServerHandler(int port) {
        try {
            // 1. 打开 ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("server started succeed!");
        while (true) {
            try {
                selector.select();
                // 5. selector 轮询就绪的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey readKey;
                while (it.hasNext()) {
                    readKey = it.next();
                    it.remove();
                    try {
                        handleInput(readKey);
                    } catch (IOException e) {
                        readKey.cancel();
                        if (readKey.channel() != null) {
                            readKey.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                System.out.println("=======channel 通道已经就绪 =======");
                // 6. 多路复用器监听到有新的客户端接入 处理新的接入请求 完成 TCP 三次握手 建立物理链接
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                // 7. 设置新建客户端参数
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                System.out.println("======= socket channel 数据准备完成 可以去读取======");
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int read = sc.read(readBuffer);
                if (read > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("server receive command = " + body);
                    String currentTime = "time".equals(body) ? new Date(System.currentTimeMillis()).toString() : "invalid command";
                    doWrite(sc, currentTime);
                } else if (read < 0) {
                    key.cancel();
                    sc.close();
                } else {
                    // 读到0字节
                    System.out.println("read 0 byte");
                }

            }
        }
    }

    private void doWrite(SocketChannel sc, String content) throws IOException {
        if (content != null && content.trim().length() > 0) {
            byte[] bytes = content.getProperty2ytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            sc.write(byteBuffer);
        }
    }


    public static void main(String[] args) {
        int port = 8080;
        MultiplexerTimeServerHandler multiplexerTimeServerHandler = new MultiplexerTimeServerHandler(port);
        new Thread(multiplexerTimeServerHandler, "nio-MultiplexerTimeServerHandler-001").start();
    }
}
