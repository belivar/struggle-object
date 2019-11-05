package com.YinglishZhi.LeetCode_10;

import org.junit.Assert;
import org.junit.Test;

/**
 * 正则表达式
 *
 * @author LDZ
 * @date 2019-11-05 14:58
 */
public class Solution {


    private static final char POINT = '.';
    private static final char STAR = '*';

    public static boolean isMatch(String s, String p) {

        char[] sChar = s.toCharArray();
        int j = 0;
        for (int i = 0; i < sChar.length; i++) {
            if (j < p.length()) {
                char c = p.charAt(j);
                if (sChar[i] == c) {
                    j++;
                } else {
                    if (c == POINT) {
                        j++;
                        continue;
                    }
                    if (c == STAR) {
                        char cPre = p.charAt(j - 1);
                        if (cPre == sChar[i] || cPre == POINT) {
                            continue;
                        }
                    }
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    // ====== test case ====

    private static final String AA = "aa";

    private static final String PATTERN_A = "a";

    private static final String PATTERN_AA = "aa";

    private static final String PATTERN_A_POINT = "a.";
    private static final String PATTERN_A_STAR = "a*";

    @Test
    public void testCase1() {
        Assert.assertTrue(isMatch(AA, PATTERN_AA));
    }

    @Test
    public void testCase2() {
        Assert.assertFalse(isMatch(AA, PATTERN_A));
    }

    @Test
    public void testCase3() {
        Assert.assertTrue(isMatch(AA, PATTERN_A_POINT));
    }

    @Test
    public void testCase4() {
        Assert.assertTrue(isMatch(AA, PATTERN_A_STAR));
    }
}
