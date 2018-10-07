package com.ww.chat02;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室 客户端（多线程）封装版
 * 实现一个客户可以正常收发信息
 */
public class OopMultiChatClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Client-----------------");
        Socket client = new Socket("localhost", 8888);
        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}
