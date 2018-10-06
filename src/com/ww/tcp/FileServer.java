package com.ww.tcp;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP 存储文件
 * 1.指定端口 使用serversocket创建服务器
 * 2.阻塞式等待连接accept
 * 3.操作：输入输出流操作
 * 4.释放资源
 */
public class FileServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        InputStream is=new BufferedInputStream(client.getInputStream());
        OutputStream os=new BufferedOutputStream(new FileOutputStream("E:\\mycode\\Net\\src\\com\\tcp.png"));
        byte[] flush=new byte[1024];
        int len=-1;
        while ((len=is.read(flush))!=-1){
            os.write(flush,0,len);
        }
        os.close();
        is.close();
        server.close();
    }
}
