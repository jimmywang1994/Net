package com.ww.chat02;

import java.io.*;
import java.net.Socket;

/**
 * 发送端 多线程
 */
public class Send implements Runnable {
    private BufferedReader reader;
    private DataOutputStream outputStream;
    private Socket client;
    private DataInputStream dis;
    private boolean isRunning = true;
    private String name;

    public Send(Socket client, String name) {
        this.name = name;
        this.client = client;
        reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            outputStream = new DataOutputStream(client.getOutputStream());
            //发送名称
            outputStream.writeUTF(name);
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    private void send(String msg) {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    /**
     * 从控制台获得字符串
     *
     * @return
     */
    private String getStrFromConsole() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = getStrFromConsole();
            if (!"".equals(msg)) {
                send(msg);
            }
        }
    }

    private void release() {
        Utils.close(dis, outputStream, client);
        this.isRunning = false;
    }
}
