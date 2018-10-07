package com.ww.chat03;

import com.ww.chat02.Receive;
import com.ww.chat02.Send;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GroupChatClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Client-----------------");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名");
        String name = reader.readLine();
        Socket client = new Socket("localhost", 8888);
        new Thread(new Send(client, name)).start();
        new Thread(new Receive(client)).start();
    }
}
