package com.ww.net;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * UDP发送端
 * 1.使用DatagramSocket 指定端口 创建发送端
 * 2.准备数据 基本类型转成字节数组
 * 3.封装成DatagramPacket 需要指定目的地
 * 4.发送数据包裹 receive()
 * 5.释放资源
 */
public class UdpTypeClient {
    public static void main(String[] args) throws Exception{
        System.out.println("发送方启动中...");
        DatagramSocket client=new DatagramSocket(8888);
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        DataOutputStream dos =new DataOutputStream(new BufferedOutputStream(baos));
        //操作数据类型 +数据
        dos.writeUTF("编码辛酸泪");
        dos.writeInt(18);
        dos.writeBoolean(false);
        dos.writeChar('a');
        dos.flush();
        byte[] datas =baos.toByteArray();
        DatagramPacket datagramPacket=new DatagramPacket(datas,0,datas.length,new InetSocketAddress("localhost",9999));
        client.send(datagramPacket);
        client.close();
    }
}
