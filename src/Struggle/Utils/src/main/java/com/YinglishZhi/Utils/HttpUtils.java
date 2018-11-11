package com.YinglishZhi.Utils;


import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpUtils {
    public static String testGet(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
             Request request = new Request.Builder().url(url).build();
             Call call = okHttpClient.newCall(request);
             try {
                     Response response = call.execute();
                     return response.body().string();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             return null;
    }

    public static void main(String[] args) {
        String s = HttpUtils.testGet("http://www.baidu.com");
        System.out.println(s);
    }
}
