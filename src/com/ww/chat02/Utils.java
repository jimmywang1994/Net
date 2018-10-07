package com.ww.chat02;

import java.io.Closeable;

public class Utils {
    //释放资源
    public static void close(Closeable... targets){
        for(Closeable target:targets){
            try {
                if(null!=target){
                    target.close();
                }
            }
            catch (Exception ex){

            }
        }
    }
}
