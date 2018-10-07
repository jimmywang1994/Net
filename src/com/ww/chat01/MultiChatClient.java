package com.ww.chat01;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室 客户端
 * 实现一个客户可以正常收发信息
 */
public class MultiChatClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Client-----------------");
        Socket client = new Socket("localhost", 8888);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        boolean isRunning = true;
        while (isRunning) {
            String msg = reader.readLine();
            //客户端发送消息
            outputStream.writeUTF(msg);
            outputStream.flush();
            //接收消息
            String recData = dis.readUTF();
            System.out.println(recData);
        }
        outputStream.close();
        dis.close();
        client.close();
    }
}
