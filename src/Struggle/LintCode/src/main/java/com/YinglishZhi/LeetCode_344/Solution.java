package com.YinglishZhi.LeetCode_344;

/**
 * 反转字符串
 *
 * @author LDZ
 * @date 2019-07-03 23:48
 */
public class Solution {

    private static void reverseString(char[] s) {
        int size = s.length;
        int last = size - 1;
        for (int i = 0; i < size / 2; ++i) {
            int difference = s[i] - s[last];
            s[i] = (char) (s[i] - difference);
            s[last] = (char) (s[last] + difference);
            last--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'1', '2', '3', '4', '5'};
        reverseString(s);
        System.out.println(s);
    }
}
