package com.YinglishZhi.LeetCode_342;

public class Solution {


    private static boolean isPowerOfFour(int n) {
        double a = Math.log10(n) / Math.log10(n);
        return 0 == ((int) a - a);
    }

    private static boolean isPowerOfFourV2(int n) {
        int numOf1 = 0;
        int num = 0;
        while (n > 0) {
            numOf1 += n & 0x01;
            num++;
            n >>= 1;
        }
        System.out.println(numOf1);
        System.out.println(num);
        return numOf1 == 1 && num % 2 == 1;


    }

    public static void main(String[] args) {
        System.out.println(isPowerOfFourV2(16));
    }
}
