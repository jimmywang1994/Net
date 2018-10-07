package com.ww.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室 服务端（多线程）
 * 多个客户可以同时正常发送多条消息
 * 问题：其他客户必须等之前的退出 要排队
 * 代码不好维护
 * 客户端读写没有分开 必须先写后读
 */
public class ThreadMultiChatServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(() -> {
                DataInputStream dis = null;
                DataOutputStream outputStream = null;
                try {
                    dis = new DataInputStream(client.getInputStream());
                    outputStream = new DataOutputStream(client.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //接收消息
                boolean isRunning = true;
                while (isRunning) {
                    String recData = null;
                    try {
                        recData = dis.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //返回消息
                    try {
                        outputStream.writeUTF(recData);
                    } catch (IOException e) {
                        e.printStackTrace();
                        isRunning = false;
                    }
                }
                try {
                    if (null != dis) {
                        dis.close();
                    }
                    if (null != outputStream) {
                        outputStream.close();
                    }
                    if (null != client) {
                        client.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
