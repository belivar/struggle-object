package com.YinglishZhi.LeetCode_771;

/**
 * @author LDZ
 * @date 2019-09-17 14:14
 */
public class Solution {

    public static void main(String[] args) {
        int result = numJewelsInStones("aA", "aAAbbbb");
        System.out.println(result);
    }

    private static int numJewelsInStones(String J, String S) {
        char[] JJ = J.toCharArray();
        int result = 0;
        for (char c : S.toCharArray()) {
            if (isHasChar(c, JJ)) {
                result++;
            }
        }

        return result;
    }

    private static boolean isHasChar(char c, char[] chars) {
        for (char cc : chars) {
            if (c == cc) {
                return true;
            }
        }
        return false;
    }

}
