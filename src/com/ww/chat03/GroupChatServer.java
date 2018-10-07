package com.ww.chat03;

import com.ww.chat02.OopMultiChatServer;
import com.ww.chat02.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GroupChatServer {
    /**
     * 多线程下使用 容器
     */
    private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<Channel>();

    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            Channel c = new Channel(client);
            all.add(c);//管理所有成员
            new Thread(c).start();
        }
    }

    /**
     * 一个channel代表一个客户
     */
    static class Channel implements Runnable {
        private Socket client;
        private DataOutputStream dos;
        private DataInputStream dis;
        private boolean isRunning;
        private String name;

        public Channel(Socket client) {
            this.client = client;
            try {
                dos = new DataOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());
                isRunning = true;
                this.name = receive();
                System.out.println("欢迎" + this.name + "来到聊天室");
                this.send("欢迎你的到来");
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        //接收消息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("--------------3");
                release();
            }
            return msg;
        }

        //发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("--------------2");
                release();
            }
        }

        //群聊 获取自己的消息，发给其他人
        private void groupSend(String msg) {
            for (Channel other : all) {
                if (other == this) {//自己
                    continue;
                }
                other.send(this.name + "说：" + msg);
            }
        }

        //释放资源
        private void release() {
            Utils.close(dis, dos, client);
            this.isRunning = false;
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("")) {
                    groupSend(msg);
                }
            }
        }
    }
}
