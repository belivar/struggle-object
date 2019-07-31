package com.YinglishZhi.test;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LDZ
 * @date 2019-07-31 11:46
 */
public class Solution {

    public static void main(String[] args) {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("orderId", "123");
        eventData.put("isExpanded", 1);
        eventData.put("expansionCouponName", "xxx");
        eventData.put("boxCount", 1);
        eventData.put("orderItemCount", 2);
        eventData.put("daysFromLastOrder", 1);
        eventData.put("membershipRemain", 12);
        eventData.put("recipientPhone", "13728182838");
        eventData.put("recipientName", "xx");
        eventData.put("recipientProvince", "xxxx");
        eventData.put("recipientCity", "xxxx");
        eventData.put("recipientDistrict", "ssss");
        eventData.put("recipientAddress", "xxx");
        eventData.put("courierName", "未知");
        eventData.put("fromType", "未知");
        eventData.put("numberOfOrder", 10);

        System.out.println(JSON.toJSONString(eventData));
    }
}
