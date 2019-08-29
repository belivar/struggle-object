package com.YinglishZhi.LeetCode_509;

/**
 * 斐波那契数
 *
 * @author LDZ
 * @date 2019-08-02 17:30
 */
public class Solution {


    public static int fib(int N) {
        if (N < 2) {
            return N;
        }
        int one = 0;
        int two = 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = one + two;
            two = one;
            one = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(fib(i));
        }
    }
}
