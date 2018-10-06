package com.ww.tcp;

import java.io.*;
import java.net.Socket;

/**
 * TCP 客户端上传文件
 * 1.建立连接 使用socket创建客户端+服务的地址和端口
 * 3.操作：输入输出流操作
 * 4.释放资源
 */
public class FileClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Client-----------------");
        Socket client = new Socket("localhost", 8888);
        InputStream is=new BufferedInputStream(new FileInputStream("E:\\mycode\\IO\\src\\com\\123.png"));
        OutputStream os=new BufferedOutputStream(client.getOutputStream());
        byte[] flush=new byte[1024];
        int len=-1;
        while ((len=is.read(flush))!=-1){
            os.write(flush,0,len);
        }
        os.flush();
        is.close();
        os.close();
        client.close();
    }
}
