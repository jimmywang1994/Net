package com.ww.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * TCP Client
 * 1.建立连接 使用socket创建客户端+服务的地址和端口
 * 3.操作：输入输出流操作
 * 4.释放资源
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Client-----------------");
        Socket client = new Socket("localhost", 8888);
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        String data = "hello";
        outputStream.writeUTF(data);
        outputStream.flush();
        outputStream.close();
        client.close();
    }
}
