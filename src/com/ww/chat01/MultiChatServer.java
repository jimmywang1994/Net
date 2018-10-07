package com.ww.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室 服务端
 * 多个客户可以同时正常发送多条消息
 * 问题：其他客户必须等之前的退出 要排队
 */
public class MultiChatServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        //接收消息
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        boolean isRunning = true;
        while (isRunning) {
            String recData = dis.readUTF();
            //返回消息
            outputStream.writeUTF(recData);
            outputStream.flush();
        }
        outputStream.close();
        dis.close();
        client.close();
    }
}
