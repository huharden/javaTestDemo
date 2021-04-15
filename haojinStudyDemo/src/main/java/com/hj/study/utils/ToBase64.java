package com.hj.study.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author hutao
 * @className ToBase64
 * @description TODO
 * @date 2021/3/9 7:29 下午
 */
public class ToBase64 {


    public static void main(String[] args) {
        // 下载网络文件
        int byteread = 0;
        String total = null;
        byte[] totalbyte = new byte[0];
        try {
//            URL url = new URL("/Users/hutao/D/AI中台/重庆网关/zhangsan.wav");
//            URLConnection conn = url.openConnection();
            //File file = new File("/Users/hutao/D/AI中台/重庆网关/zhangsan.wav");
            FileInputStream file = new FileInputStream("/Users/hutao/D/AI中台/重庆网关/zhangsan.wav");

            byte[] buffer = new byte[1204];
            while ((byteread = file.read(buffer)) != -1) {
                //拼接流，这样写是保证文件不会被篡改
                totalbyte = byteMerger(totalbyte, buffer, byteread);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tobase64 = new BASE64Encoder().encode(totalbyte);
        //这就是最终得到的结果了
        System.out.print(tobase64);
        //如果要保存到本地的话就放开下面的代码

        try {
            decoderBase64File(tobase64, "/Users/hutao/D/AI中台/重庆网关/yuyintingxie.json");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 拼接byte[]类型数据
     */
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2, int byteread) {
        byte[] byte_3 = new byte[byte_1.length + byteread];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byteread);
        return byte_3;
    }

    /**
     * 解析base64,并保存到本地
     */
    public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
}

