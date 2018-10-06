package com.ww.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP Server
 * 1.指定端口 使用serversocket创建服务器
 * 2.阻塞式等待连接accept
 * 3.操作：输入输出流操作
 * 4.释放资源
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String recData = dis.readUTF();
        System.out.println("一个客户端建立了连接");
        System.out.println("收到数据:" + recData);
        dis.close();
        server.close();
    }
}
