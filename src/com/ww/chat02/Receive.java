package com.ww.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收端 多线程
 */
public class Receive implements Runnable {
    private Socket client;
    private DataInputStream dis;
    private boolean isRunning = true;

    public Receive(Socket client) {
        this.client = client;
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            release();
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            if (!"".equals(msg)) {
                System.out.println(msg);
            }
        }
    }

    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            release();
        }
        return msg;
    }

    private void release() {
        Utils.close(dis, client);
        this.isRunning = false;
    }
}
