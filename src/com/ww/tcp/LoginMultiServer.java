package com.ww.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登陆 多客户端请求
 */
public class LoginMultiServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        boolean isRunning = true;
        while (isRunning) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }
        server.close();
    }
    //一个channel代表一个客户端
    static class Channel implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;

        public Channel(Socket client) throws IOException {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
               release();
            }
        }

        private String receive() {
            String recData = "";
            try {
                recData = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return recData;
        }

        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            String name = "";
            String pwd = "";
            String[] datas = receive().split("&");
            for (String info : datas) {
                String[] userInfo = info.split("=");
                if (userInfo[0].equals("name")) {
                    System.out.println("你的用户名是：" + userInfo[1]);
                    name = userInfo[1];
                } else if (userInfo[0].equals("password")) {
                    System.out.println("你的密码是：" + userInfo[1]);
                    pwd = userInfo[1];
                }
            }
            if (name.equals("wangwei") && pwd.equals("123")) {
                send("你的用户名和密码正确");
            } else {
                send("你的用户名或密码错误");
            }
            System.out.println("一个客户端建立了连接");
            release();
        }
        private void release(){
            try {
                if(null!=dis) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null!=dos) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null!=client) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}