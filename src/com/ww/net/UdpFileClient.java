package com.ww.net;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * UDP发送端 文件上传
 * 1.使用DatagramSocket 指定端口 创建发送端
 * 2.准备数据 基本类型转成字节数组
 * 3.封装成DatagramPacket 需要指定目的地
 * 4.发送数据包裹 receive()
 * 5.释放资源
 */
public class UdpFileClient {
    public static void main(String[] args) throws Exception{
        System.out.println("发送方启动中...");
        DatagramSocket client=new DatagramSocket(8888);
        byte[] datas= IOUtils.fileToByteArray("E:\\mycode\\IO\\src\\com\\123.png");
        DatagramPacket datagramPacket=new DatagramPacket(datas,0,datas.length,new InetSocketAddress("localhost",9999));
        client.send(datagramPacket);
        client.close();
    }
}
