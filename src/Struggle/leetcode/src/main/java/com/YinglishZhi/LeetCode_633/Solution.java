package com.YinglishZhi.LeetCode_633;

import java.util.HashSet;

/**
 * 平方数之和
 *
 * @author LDZ
 * @date 2019-07-12 21:18
 */
public class Solution {

    public static boolean judgeSquareSum(int c) {
        int cc = (int) Math.sqrt(c);
        if (cc * cc == c) {
            return true;
        }

        for (int i = 0; i <= cc; i++) {
            int b = c - i * i;
            if (isSquareNum(b)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSquareNum(int x) {
        int xx = (int) Math.sqrt(x);
        return xx * xx == x;
    }

    public static boolean judgeSquareSumV2(int c) {
        HashSet<Integer> squareNum = new HashSet<>();
        int cc = (int) Math.sqrt(c);
        for (int i = 0; i <= cc; i++) {
            squareNum.add(i * i);
            if (squareNum.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 费平方和定理
     * c 是 4k+3 型的质数因子个数为偶数的时候 可以拆成两个平方和
     *
     * @param c
     * @return
     */
    public static boolean judgeSquareSumV3(int c) {
        for (int i = 2; i * i < c; i++) {
            if (c % i != 0) {
                continue;
            }

            int count = 0;

            while (c % i == 0) {
                count = count + 1;
                c = c / i;
            }
            if (i % 4 == 3 && count % 2 != 0) {
                return false;
            }
        }
        return c % 4 != 3;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSumV3(8));
    }
}