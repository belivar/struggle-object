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
        if (null == p || null == s) {
            return false;
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == STAR && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }


        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == POINT || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == STAR) {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != POINT) {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];

    }

    // ====== test case ====

    private static final String AA = "aa";
    private static final String AB = "ab";

    private static final String PATTERN_A = "a";

    private static final String PATTERN_AA = "aa";

    private static final String PATTERN_A_POINT = "a.";
    private static final String PATTERN_A_STAR = "a*";

    private static final String PATTERN_POINT_STAR = ".*";

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

    @Test
    public void testCase5() {
        Assert.assertTrue(isMatch(AB, PATTERN_POINT_STAR));
    }
}
