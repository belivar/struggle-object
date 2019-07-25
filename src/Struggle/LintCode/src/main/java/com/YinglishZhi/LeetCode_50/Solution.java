package com.YinglishZhi.LeetCode_50;

/**
 * @author LDZ
 * @date 2019-07-18 14:44
 */
public class Solution {

    public static double myPow(double x, int n) {
        double sum = 1;
        double tmp = x;

        while (n != 0) {
            if (1 == (n & 1)) {
                sum = sum * tmp;
            }
            tmp = tmp * tmp;
            n = n >> 1;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(myPow(4d, 2));
    }
}
