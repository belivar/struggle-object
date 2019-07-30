package com.YinglishZhi.LeetCode_12;

public class Solution {

    private static String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        StringBuilder res = new StringBuilder();
        while (index < nums.length) {
            while (num >= nums[index]) {
                res.append(romans[index]);
                num = num - nums[index];
            }
            index++;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String res = intToRoman(4);
        System.out.println(res);
    }
}
