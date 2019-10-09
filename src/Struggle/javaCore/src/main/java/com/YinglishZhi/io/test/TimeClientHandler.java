package com.YinglishZhi.io.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LDZ
 * @date 2019-09-29 17:00
 */
public class TimeClientHandler implements Runnable {

    private SocketChannel socketChannel;

    private int port;

    private Selector selector;

    private String host;

    private boolean stop;


    public TimeClientHandler(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!stop) {
            try {
                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey writeKey = null;
                while (iterator.hasNext()) {
                    writeKey = iterator.next();
                    iterator.remove();
                    try {
                        handlerInput(writeKey);
                    } catch (IOException e) {
                        if (writeKey != null) {
                            writeKey.cancel();
                            if (writeKey.channel() != null) {
                                writeKey.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlerInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                } else {
                    System.exit(1);
                }
            }

            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int read = sc.read(readBuffer);
                if (read > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("现在时间为：" + body);
                    this.stop = true;
                } else if (read < 0) {
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    //读到0字节
                }
            }
        }
    }


    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel sc) throws IOException {
        byte[] bytes = "time".getBytes();
        ByteBuffer writeBuff = ByteBuffer.allocate(bytes.length);
        writeBuff.put(bytes);
        writeBuff.flip();
        socketChannel.write(writeBuff);
        if (!writeBuff.hasRemaining()) {
            System.out.println("客户端发送命令成功");
        }
    }

    public static void main(String[] args) {
        TimeClientHandler timeClientHandler = new TimeClientHandler(null, 8080);
        new Thread(timeClientHandler, "nio client 1").start();
    }
}
