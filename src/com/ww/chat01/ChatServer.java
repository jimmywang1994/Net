package com.ww.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室 服务端
 */
public class ChatServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        //接收消息
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String recData = dis.readUTF();
        //返回消息
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        outputStream.writeUTF(recData);
        outputStream.flush();
        outputStream.close();
        dis.close();
        client.close();
    }
}
