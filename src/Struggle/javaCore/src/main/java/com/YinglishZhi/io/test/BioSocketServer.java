package com.YinglishZhi.io.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO
 *
 * @author LDZ
 * @date 2019-08-11 12:10
 */
public class BioSocketServer {

    public static int DEFAULT_PORT = 8083;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            System.out.println("监听来自于" + DEFAULT_PORT + "的端口");

            serverSocket = new ServerSocket(DEFAULT_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                SocketServerThread socketServerThread = new SocketServerThread(socket);
                new Thread(socketServerThread).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (BioSocketServer.class) {
            try {
                BioSocketServer.class.wait();
            } catch (Exception e) {

            }
        }

    }


    static class SocketServerThread implements Runnable {
        private Socket socket;

        SocketServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream in = null;
            OutputStream out = null;

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();

                int sourcePort = socket.getPort();

                int maxLength = 1024;

                byte[] contextBytes = new byte[maxLength];

                int readLength = in.read(contextBytes, 0, maxLength);

                String message = new String(contextBytes, 0, readLength);

                System.out.println("服务器收到来自于端口" + sourcePort + "的信息" + message);

                out.write("回发响应信息".getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (null != in) {
                        in.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                    if (null != this.socket) {
                        socket.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
