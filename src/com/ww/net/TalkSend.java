package com.ww.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送端
 */
public class TalkSend implements Runnable {
    private DatagramSocket client;
    private BufferedReader reader;
    private String toIP;
    private int toPort;
    public TalkSend(int port,String toIP,int toPort){
        this.toIP=toIP;
        this.toPort=toPort;
        try {
            client=new DatagramSocket(port);
            reader=new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true){
            String data= null;
            try {
                data = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] datas=data.getBytes();
            DatagramPacket datagramPacket=new DatagramPacket(datas,0,datas.length,new InetSocketAddress(this.toIP,this.toPort));
            try {
                client.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (data.equals("bye")){
                break;
            }
        }
        client.close();
    }
}
