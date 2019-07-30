package com.YinglishZhi.LeetCode_38;

public class Solution {

    public static String countAndSay(int n) {
        String be = "1";
        for (int i = 0; i < n - 1; i++) {
            be = getNext(be);
        }
        return be;
    }

    public static String countAndSayV2(int n) {
        if (1 == n) {
            return "1";
        }
        String origin = countAndSayV2(n - 1);
        StringBuilder sb = new StringBuilder();
        int len = origin.length();
        int count = 1;
        for (int i = 0; i < len; i++) {
            if (i == len - 1 || origin.charAt(i) != origin.charAt(i + 1)) {
                sb.append(count).append(origin.charAt(i));
                count = 1;
            } else {
                count++;
            }
        }
        return sb.toString();
    }

    public static String getNext(String pre) {
        String result = "";
        int k = 1;
        for (int i = 0; i < pre.length(); i++) {
            if (i == pre.length() - 1 || pre.charAt(i) != pre.charAt(i + 1)) {
                result = result + k + pre.charAt(i);
                k = 1;
            } else {
                k++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(countAndSayV2(1));
        System.out.println(countAndSayV2(2));
        System.out.println(countAndSayV2(3));
        System.out.println(countAndSayV2(4));
        System.out.println(countAndSayV2(5));
        System.out.println(countAndSayV2(6));
        System.out.println(countAndSayV2(7));
    }
}
