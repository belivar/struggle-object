package com.YinglishZhi.LeetCode_7;

public class Solution {

    private static int reverse(int x) {
        long result = 0;
        if (x == 0) {
            return 0;
        }
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return (result < Integer.MAX_VALUE && result > Integer.MIN_VALUE) ? (int) result : 0;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(reverse(1534236469));
    }
}
