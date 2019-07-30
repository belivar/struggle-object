package com.YinglishZhi.LeetCode_3;

import java.util.HashMap;

/**
 * 无重复字符最长子串
 *
 * @author LDZ
 * @date 2019-07-16 20:12
 */
public class Solution {

    public static int lengthOfLongestSubstring(String s) {
        if (null == s) {
            return 0;
        }

        /**
         * 记录字符上一次出现的位置
         */
        HashMap<Character, Integer> map = new HashMap<>();

        int max = 0;

        /**
         * 最近出现的重复字符的位置
         */
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            Integer index = map.get(ch);
            if (null != index) {
                pre = Math.max(pre, index);
            }
            max = Math.max(max, i - pre);
            map.put(ch, i);
        }

        return max;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkes"));
    }
}
