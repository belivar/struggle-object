package com.YinglishZhi.banner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LDZ
 * @date 2019-11-05 11:25
 */
public class jsonLoader {


    private static final String JSON_LOCATION = "/com/YinglishZhi/res/test.json";

    static {

    }


    @Data
    class JsonResult {
        private String name;
        private int corePoolSize;
        private int maximumPoolSize;
        private int workQueueSize;
        private int keepAliveTime;
    }

    public static void main(String[] args) {
        InputStream inputStream = jsonLoader.class.getResourceAsStream(JSON_LOCATION);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        List<JsonResult> result = new Gson().fromJson(inputStreamReader, new TypeToken<ArrayList<JsonResult>>() {
        }.getType());
        System.out.println(result);
    }
}
