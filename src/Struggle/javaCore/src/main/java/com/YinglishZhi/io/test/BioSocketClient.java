package com.YinglishZhi.io.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.concurrent.CountDownLatch;


/**
 * Bio
 *
 * @author LDZ
 * @date 2019-08-11 12:22
 */
public class BioSocketClient {

    public static void main(String[] args) {
        Integer clientNumber = 20;

        CountDownLatch countDownLatch = new CountDownLatch(clientNumber);

        for (int index = 0; index < clientNumber; index++, countDownLatch.countDown()) {
            ClientRequestThread clientRequestThread = new ClientRequestThread(countDownLatch, index);
            new Thread(clientRequestThread).start();
        }

        synchronized (BioSocketClient.class) {
            try {
                BioSocketClient.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class ClientRequestThread implements Runnable {

        private CountDownLatch countDownLatch;

        private Integer clientIndex;

        public ClientRequestThread(CountDownLatch countDownLatch, Integer clientIndex) {
            this.countDownLatch = countDownLatch;
            this.clientIndex = clientIndex;
        }

        @Override
        public void run() {
            Socket socket = null;

            OutputStream clientRequest = null;
            InputStream clientResponse = null;

            try {
                socket = new Socket("localhost", BioSocketServer.DEFAULT_PORT);
                clientRequest = socket.getOutputStream();
                clientResponse = socket.getInputStream();

                countDownLatch.await();

                clientRequest.write(("这是第" + clientIndex + "个客户端请求。over").getProperty2ytes());
                clientRequest.flush();

                System.out.println("这是第" + clientIndex + "个客户端的请求发送完成，等待服务器返回信息");

                int maxLength = 1024;
                byte[] contextBytes = new byte[maxLength];

                int readLength;

                StringBuilder message = new StringBuilder();

                while ((readLength = clientResponse.read(contextBytes, 0, maxLength)) != -1) {
                    message.append(new String(contextBytes, 0, readLength));
                }

                message = new StringBuilder(URLDecoder.decode(message.toString(), "UTF-8"));
                System.out.println("第" + this.clientIndex + "个客户端接收到来自服务器的信息:" + message);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (clientRequest != null) {
                        clientRequest.close();
                    }
                    if (clientResponse != null) {
                        clientResponse.close();
                    }
                } catch (IOException ignored) {

                }
            }

        }
    }
}
