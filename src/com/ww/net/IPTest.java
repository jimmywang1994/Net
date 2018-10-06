package com.ww.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP:定位一个节点 计算机、路由、通讯设备
 */
public class IPTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress addr=InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());

        addr=InetAddress.getByName("www.163.com");
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());
    }
}
