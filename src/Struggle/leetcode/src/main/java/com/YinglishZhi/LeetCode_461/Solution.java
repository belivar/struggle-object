package com.YinglishZhi.LeetCode_461;

/**
 * 汉明距离
 *
 * @author LDZ
 * @date 2019-08-24 14:13
 */
public class Solution {


    public static int hammingDistance(int x, int y) {
        int temp = x ^ y;
        return find1(temp);
    }


    public static int find1(int num) {
        int result = 0;
        while (num > 0) {
            num = num & num - 1;
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(     hammingDistance(1, 4));
    }
}



