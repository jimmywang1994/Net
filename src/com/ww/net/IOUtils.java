package com.ww.net;

import java.io.*;

public class IOUtils {
    public static void main(String[] args) {
        byte[] datas=fileToByteArray("E:\\mycode\\IO\\src\\com\\123.png");
        System.out.println("data size:"+datas.length);
        byteArrayToFile(datas,"E:\\mycode\\IO\\src\\com\\666.png");
    }

    public static byte[] fileToByteArray(String path){
        File file=new File(path);
        byte[] dest = null;
        InputStream inputStream=null;
        ByteArrayOutputStream bos = null;
        try {
            inputStream=new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] car=new byte[1024*10];
            int len=-1;
            while ((len=inputStream.read(car))!=-1){
                bos.write(car, 0, len);
            }
            bos.flush();
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null!=inputStream){
                    inputStream.close();
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static void byteArrayToFile(byte[] src,String filePath){
        //1、创建源
        File dest = new File(filePath);
        //2、选择流
        InputStream  is =null;
        OutputStream os =null;
        try {
            is =new ByteArrayInputStream(src);
            os = new FileOutputStream(dest);
            //3、操作 (分段读取)
            byte[] flush = new byte[5]; //缓冲容器
            int len = -1; //接收长度
            while((len=is.read(flush))!=-1) {
                os.write(flush,0,len);			//写出到文件
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //4、释放资源
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
