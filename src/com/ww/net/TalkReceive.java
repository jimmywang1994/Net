package com.ww.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端
 */
public class TalkReceive implements Runnable {
    private DatagramSocket server;
    private String from;
    public TalkReceive(int port, String from) {
        this.from = from;
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            byte[] container = new byte[1024 * 60];
            DatagramPacket datagramPacket = new DatagramPacket(container, 0, container.length);
            try {
                server.receive(datagramPacket);//阻塞式
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] datas = datagramPacket.getData();
            int len = datas.length;
            String data = new String(datas, 0, len);
            System.out.println(this.from + "：" + data);
            if (data.equals("bye")) {
                break;
            }
        }
        server.close();
    }
}
