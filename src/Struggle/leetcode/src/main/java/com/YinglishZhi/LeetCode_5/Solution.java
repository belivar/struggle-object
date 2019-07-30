package com.YinglishZhi.LeetCode_5;

/**
 * 最长回文子串
 *
 * @author LDZ
 * @date 2019-07-16 20:51
 */
public class Solution {

    public static String longestPalindrome(String s) {

        if (null == s || s.length() <= 1) {
            return s;
        }

        int len = s.length();
        int maxLength = 1;

        String longestPalindrome = null;

        char[] sChar = s.toCharArray();

        int[][] table = new int[len][len];

        for (int i = 0; i < len; i++) {
            table[i][i] = 1;
            longestPalindrome = "" + sChar[i];
        }

        for (int i = 0; i < len - 1; i++) {
            if (sChar[i] == sChar[i + 1]) {
                table[i][i + 1] = 1;
                longestPalindrome = "" + sChar[i] + sChar[i + 1];
                maxLength = 2;
            }
        }

        // 从长度为3的开始轮
        for (int l = 3; l <= len; l++) {
            // 起点 i 从 0开始
            for (int i = 0; i <= len - l; i++) {
                // 长度为 l 情况下 j点坐标
                int j = i + l - 1;
                if (sChar[i] == sChar[j]) {
                    table[i][j] = table[i + 1][j - 1];
                    if (table[i][j] == 1 && l > maxLength) {
                        longestPalindrome = s.substring(i, j + 1);
                        maxLength = l;
                    }
                } else {
                    table[i][j] = 0;
                }
            }
        }


        return longestPalindrome;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("ab"));
    }
}
