package com.ww.net;

import java.net.InetSocketAddress;

/**
 * 端口
 * 区分软件
 * 两个字节 0-65535 UDP TCP
 * 同一个协议下端口不能冲突
 * 端口越大越好
 */
public class PortTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",8080);
        InetSocketAddress inetSocketAddress2=new InetSocketAddress("localhost",9000);
        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress.getAddress());
    }
}
