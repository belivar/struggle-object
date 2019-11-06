package com.YinglishZhi.io.test.chatroom.server;

import com.YinglishZhi.io.test.chatroom.util.Util;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @author LDZ
 * @date 2019-11-06 10:46
 */
public class ServerSocketProcess {

    private static SocketChannel accept(Selector selector, ServerSocketChannel serverSocketChannel) {

        SocketChannel socketChannel = null;

        try {
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

            socketChannel.write(Util.charset.encode("please write your name"));

        } catch (IOException e) {
            System.out.println("Error while configure socket channel :" + e);
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return socketChannel;
    }


    public static void read(Selector selector, SelectionKey selectionKey, List<SocketChannel> socketChannelList) {

        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        StringBuilder content = new StringBuilder();



    }
}
