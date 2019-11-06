package com.YinglishZhi.io.test.chatroom.client;


import com.YinglishZhi.io.test.chatroom.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
/**
 * @author LDZ
 * @date 2019-11-06 10:13
 */
public class Dealer {
    /**
     * 从SocketChannel中读取信息
     *
     * @param channel
     * @throws IOException
     */
    public static void read(SocketChannel channel) throws IOException {

        /**
         * 初始化缓冲区
         */
        ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024);
        /**
         * 读到的字节数
         */
        int num = 0;
        String content = "";
        while ((num = channel.read(buffer)) > 0) {
            buffer.flip();
            content += Util.charset.decode(buffer);
        }
        //若系统发送通知名字已经存在，则需要换个昵称
        if(Util.USER_EXIST.equals(content)) {
//            name = "";
            System.out.println("name has exists.");
        }
        System.out.println(content);
    }

    /**
     * 想SocketChannel中写入数据
     *
     * @param channel
     */
    public static void write(SocketChannel channel) {

//        /**
//         * 从消息队列中获取要发送的消息
//         */
//        String msg = MsgQueue.getInstance().get();
//        if (msg == null) {
//            /**
//             * 如果消息队列中没有要发送的消息，则返回。
//             */
//            return;
//        }
//        /**
//         * 初始化缓冲区
//         */
//        ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024);
//
//        /**
//         * 把消息放到缓冲区中
//         */
//        buffer.put(msg.getBytes());
//
//        /**
//         * 重置缓冲区指针
//         */
//        buffer.flip();
//        try {
//            /**
//             * 把缓冲区中的数据写到SocketChannel里
//             */
//            channel.write(buffer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
