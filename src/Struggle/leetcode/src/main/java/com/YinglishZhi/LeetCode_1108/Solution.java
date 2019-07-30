package com.YinglishZhi.LeetCode_1108;

/**
 * ip 地址无效化
 *
 * @author LDZ
 * @date 2019-07-24 14:50
 */
public class Solution {

    /**
     * @param address
     * @return
     */
    public static String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            sb.append('.' == address.charAt(i) ? "[.]" : address.charAt(i));
        }
        return sb.toString();
    }
}
