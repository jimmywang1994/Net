package com.ww.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登陆 双向
 */
public class LoginTwoWayServer {
    public static void main(String[] args) throws IOException {
        System.out.println("----------Server----------------");
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String recData = dis.readUTF();
        String name="";
        String pwd="";
        String[] datas = recData.split("&");
        for (String info : datas) {
            String[] userInfo = info.split("=");
            if(userInfo[0].equals("name")){
                System.out.println("你的用户名是："+userInfo[1]);
                name=userInfo[1];
            }else if(userInfo[0].equals("password")){
                System.out.println("你的密码是："+userInfo[1]);
                pwd=userInfo[1];
            }
        }DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        if(name.equals("wangwei")&&pwd.equals("123")){
            dos.writeUTF("你的用户名和密码正确");
        }else{
            dos.writeUTF("你的用户名或密码错误");
        }
        System.out.println("一个客户端建立了连接");
//        System.out.println("收到数据:" + recData);
        dos.close();
        dis.close();
        server.close();
    }
}
