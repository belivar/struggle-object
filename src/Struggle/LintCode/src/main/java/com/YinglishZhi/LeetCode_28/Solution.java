package com.YinglishZhi.LeetCode_28;

public class Solution {

    public static int strStr(String haystack, String needle) {
        if (0 == needle.length()) {
            return 0;
        }
        if (0 == haystack.length() || haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            int k = i;
            if (haystack.charAt(k) == needle.charAt(j)) {
                while (j < needle.length()) {
                    if (haystack.charAt(k) != needle.charAt(j)) {
                        break;
                    }
                    k++;
                    j++;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(strStr("a", "a"));
    }
}
