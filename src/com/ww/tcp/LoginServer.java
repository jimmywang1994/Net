package com.ww.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登陆 单向
 */
public class LoginServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String recData = dis.readUTF();
        String[] datas = recData.split("&");
        for (String info : datas) {
            String[] userInfo = info.split("=");
            System.out.println(userInfo[0] +"："+ userInfo[1]);
        }
        System.out.println("一个客户端建立了连接");
//        System.out.println("收到数据:" + recData);
        dis.close();
        server.close();
    }
}
