package com.YinglishZhi.LeetCode_504;

/**
 * 7 进制
 *
 * @author LDZ
 * @date 2019-07-06 22:41
 */
public class Solution {

    public static String convertToBase7(int num) {
        if (0 == num) {
            return "0";
        }
        boolean isNegative = num < 0;
        StringBuilder result = new StringBuilder();
        while (num != 0) {
            int remainder = num % 7;
            num = num / 7;
            result.insert(0, remainder > 0 ? remainder : -remainder);
        }
        if (isNegative) {
            result.insert(0, "-");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String result = convertToBase7(101);
        System.out.println(result);
    }
}
