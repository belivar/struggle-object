package com.YinglishZhi.LeetCode_326;

/**
 * 3 的幂
 *
 * @author LDZ
 * @date 2019-07-02 22:40
 */
public class Solution {

    private static boolean isPowerOfThree(int n) {
        if (0 == n) {
            return false;
        }
        if (1 == n) {
            return true;
        }
        int a = n % 10;
        System.out.println(a);
        if (a != 1 && a != 3 && a != 7 && a != 9) {
            return false;
        }
        while (n > 3) {
            if (n % 3 != 0) {
                return false;
            }
            n = n / 3;
        }
        return 3 == n;
    }

    private static boolean isPowerOfThreeV2(int n) {
        double re = Math.log10(n) / Math.log10(3);
        return ((int) re - re) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThreeV2(243));
    }
}
