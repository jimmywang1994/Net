package com.ww.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 爬虫原理 +模拟浏览器
 */
public class SpiderTest01 {
    public static void main(String[] args) throws Exception {
        //获取URL
        //URL url=new URL("https://www.jd.com");
        URL url=new URL("https://www.dianping.com");
        //下载资源
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        InputStream inputStream=url.openStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
        String msg=null;
        while (null!=(msg=reader.readLine())){
            System.out.println(msg);
        }
        reader.close();
        //分析
    }
}
