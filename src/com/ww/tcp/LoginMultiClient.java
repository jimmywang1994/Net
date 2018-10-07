package com.ww.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 模拟登陆 多客户端请求
 */
public class LoginMultiClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Client-----------------");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名");
        String name = console.readLine();
        System.out.println("请输入密码");
        String password = console.readLine();
        Socket client = new Socket("localhost", 8888);
        new Send(client).send("username=" + name + "&password=" + password);
        String result = new Receive(client).receive();
        System.out.println(result);
        new Send(client).release();
    }

    static class Send {
        private Socket client;
        private DataOutputStream outputStream;

        public Send(Socket client) {
            this.client = client;
            try {
                outputStream = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(String msg) {
            try {
                outputStream.writeUTF(msg);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            release();
        }
        public void release(){
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Receive {
        private DataInputStream inputStream;
        private Socket client;
        public Receive(Socket client){
            this.client=client;
            try {
                inputStream=new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public String receive(){
            String result="";
            try {
                result= inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        public void release(){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
