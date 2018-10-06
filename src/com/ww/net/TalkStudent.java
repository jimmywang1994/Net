package com.ww.net;

/**
 * 加入多线程
 */
public class TalkStudent {
    public static void main(String[] args) {
        new Thread(new TalkSend(7777,"localhost",8888)).start();//发送

        new Thread(new TalkReceive(7878,"老师")).start();//接收
    }
}
