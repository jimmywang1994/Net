package com.ww.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 模拟登陆 双向
 */
public class LoginTwoWayClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Client-----------------");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名");
        String name = console.readLine();
        System.out.println("请输入密码");
        String password = console.readLine();
        Socket client = new Socket("localhost", 8888);
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        outputStream.writeUTF("name=" + name + "&password=" + password);
        outputStream.flush();
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String result = dis.readUTF();
        System.out.println(result);
        outputStream.close();
        client.close();
    }
}
