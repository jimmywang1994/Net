package com.ww.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * UDP接收端
 * 1.使用DatagramSocket 指定端口 创建接收端
 * 2.准备容器 封装成DatagramPacket
 * 3.阻塞式接收数据包裹 receive()
 * 4.分析数据  byte[] getData() getLength()
 * 5.释放资源
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        System.out.println("接收方启动中...");
        DatagramSocket server=new DatagramSocket(9999);
        byte[] container=new byte[1024*60];
        DatagramPacket datagramPacket=new DatagramPacket(container,0,container.length);
        server.receive(datagramPacket);//阻塞式
        byte[] datas=datagramPacket.getData();
        int len=datas.length;
        System.out.println(new String(datas,0,len));
        server.close();

    }
}
