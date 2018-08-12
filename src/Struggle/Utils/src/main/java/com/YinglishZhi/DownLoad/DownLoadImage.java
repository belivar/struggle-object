package com.YinglishZhi.DownLoad;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class DownLoadImage {
    public static void downloadImg(String url, String path) {
        if (!url.startsWith("http") || null == url || null == path) {
            return;
        }
        //根据传进来的url构造文件名
        String filename = url.substring(url.lastIndexOf("/") + 1);

        InputStream in = null;
        OutputStream out = null;

        int num = 0;
        //1k的数据缓冲
        byte[] bs = new byte[1024];
        //输出的文件流
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        try {
            out = new FileOutputStream(path + filename);
            //构造URL
            URL imgUrl = new URL(url);
            //打开连接
            HttpURLConnection con = (HttpURLConnection) imgUrl.openConnection();
            //设置连接超时
            con.setConnectTimeout(1000);
            //输入流
            in = con.getInputStream();
            while ((num = in.read(bs)) != -1) {
                out.write(bs, 0, num);
            }
        } catch (FileNotFoundException e) {
            log.error("找不到网络图片。。。");
        } catch (NullPointerException e) {
            log.error("找不到网络图片。。。");
        } catch (MalformedURLException e) {
            log.error("");
        } catch (Exception e) {
            log.error("未知错误");
        } finally {
            try {
                out.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        downloadImg("http://uploads.xuexila.com/allimg/1708/28-1FP1213111.jpg", "/Users/liudezhi/Pictures/test/");
    }
}
