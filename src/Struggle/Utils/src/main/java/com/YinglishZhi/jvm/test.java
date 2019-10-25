package com.YinglishZhi.jvm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author LDZ
 * @date 2019-10-15 16:36
 */
public class test {
    public static void main(String[] args) {

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1", "21");
        linkedHashMap.put("4", "24");
        linkedHashMap.put("3", "23");
        linkedHashMap.put("2", "22");
        Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();


        String json = (JSON.toJSONString(linkedHashMap));
        ArrayList arrayList = new ArrayList<>(new Gson().fromJson(json, LinkedHashMap.class).keySet());
        System.out.println(arrayList);
        LinkedHashMap linkedHashMap1 = JSONObject.parseObject(json, LinkedHashMap.class);
        System.out.println(linkedHashMap.equals(linkedHashMap1));
        System.out.println("====main====");
    }
}
