package com.ww.net;

public class TalkTeacher {
    public static void main(String[] args) {
        new Thread(new TalkSend(6666,"localhost",7878)).start();//发送
        new Thread(new TalkReceive(8888,"学生")).start();//接收
    }
}
