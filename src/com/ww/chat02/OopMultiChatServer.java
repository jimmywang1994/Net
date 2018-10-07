package com.ww.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室 服务端（多线程）封装版
 * 多个客户可以同时正常发送多条消息
 * 问题：其他客户必须等之前的退出 要排队
 * 代码不好维护
 * 客户端读写没有分开 必须先写后读
 */
public class OopMultiChatServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }
    }

    /**
     * 一个channel代表一个客户
     */
    static class Channel implements Runnable {
        private Socket client;
        private DataOutputStream dos;
        private DataInputStream dis;
        private boolean isRunning;

        public Channel(Socket client) {
            this.client = client;
            try {
                dos = new DataOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());
                isRunning = true;
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        //接收消息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("--------------3");
                release();
            }
            return msg;
        }

        //发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("--------------2");
                release();
            }
        }

        //释放资源
        private void release() {
            Utils.close(dis, dos, client);
            this.isRunning = false;
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("")) {
                    send(msg);
                }
            }
        }
    }
}
