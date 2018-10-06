package com.ww.net;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 统一资源定位器
 */
public class URLTest01 {
    public static void main(String[] args) throws MalformedURLException {
        URL url=new URL("https://www.baidu.com/s?wd=%E5%9B%9B%E7%BA%A7%E5%86%9B%E5%A3%AB%E9%95%BF&ie=UTF-8");
        //获取四个值
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getFile());
        System.out.println(url.getPath());

        //参数
        System.out.println(url.getQuery());
    }
}
