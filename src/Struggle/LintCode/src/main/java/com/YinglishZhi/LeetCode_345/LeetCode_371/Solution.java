package com.YinglishZhi.LeetCode_345.LeetCode_371;

/**
 * 两数之和
 *
 * @author LDZ
 * @date 2019-07-04 23:32
 */
public class Solution {

    public static int getSum(int a, int b) {
        if (0 == b) {
            return a;
        }

        int sum = a ^ b;
        int carry = (a & b) << 1;

        return getSum(sum, carry);
    }

    public static void main(String[] args) {
        int a = getSum(12, 2);
        System.out.println(a);
    }
}
