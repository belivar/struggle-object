package com.YinglishZhi.LeetCode_868;


/**
 * binary gap
 *
 * @author LDZ
 * @date 2019-07-07 00:03
 */
public class Solution {
    public static int binaryGap(int N) {
        int max = 0;
        Integer pre1 = null;
        int i = 0;
        while (0 != N) {
            if (1 == (N & 0x01)) {
                max = null == pre1 ? 0 : (i - pre1 > max ? i - pre1 : max);
                pre1 = i;
            }
            N = N >> 1;
            i++;
        }
        return max;
    }

    public static void main(String[] args) {
        int max = binaryGap(16);
        System.out.println(max);
    }
}
