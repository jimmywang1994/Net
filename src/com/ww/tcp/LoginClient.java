package com.ww.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 模拟登陆 单向
 */
public class LoginClient {
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
        outputStream.close();
        client.close();
    }
}
