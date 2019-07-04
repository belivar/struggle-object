package com.YinglishZhi.LeetCode_345;

/**
 * 反转元音
 *
 * @author LDZ
 * @date 2019-07-04 00:02
 */
public class Solution {

    public static String reverseVowels(String s) {
        int size = s.length();
        int last = size - 1;

        char[] ss = s.toCharArray();
        int i = 0;
        while (i < last) {
            if (isVowel(ss[i]) && isVowel(ss[last])) {
                int diff = ss[i] - ss[last];
                ss[i] = (char) (ss[i] - diff);
                ss[last] = (char) (ss[last] + diff);
                i++;
                last--;
            }
            if (!isVowel(ss[i])) {
                i++;
            }
            if (!isVowel(ss[last])) {
                last--;
            }
        }
        return new String(ss);
    }

    private static boolean isVowel(char a) {
        return 'a' == a || 'e' == a || 'i' == a || 'o' == a || 'u' == a;
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }
}
