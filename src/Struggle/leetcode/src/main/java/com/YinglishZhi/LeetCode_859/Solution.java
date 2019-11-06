package com.YinglishZhi.LeetCode_859;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author LDZ
 * @date 2019-11-06 16:47
 */
public class Solution {

    public static boolean buddyStrings(String A, String B) {

        // 长度不等不行
        if (A.length() != B.length()) {
            return false;
        }

        if (A.equals(B)) {
            List<Character> characters = new ArrayList<>();
            for (char c : A.toCharArray()) {
                if (characters.contains(c)) {
                    return true;
                } else {
                    characters.add(c);
                }
            }
        } else {
            int pre = -1, next = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (pre > -1 && next > -1) {
                        return false;
                    } else {
                        if (pre == -1) {
                            pre = i;
                        } else {
                            next = i;
                        }
                    }
                }
            }
            if (next == -1) {
                return false;
            }

            return A.charAt(next) == B.charAt(pre) && A.charAt(pre) == B.charAt(next);
        }

        return false;
    }

    // ======= test case ====
    private static final String CASEA1 = "ab", CASEB1 = "ba";
    private static final String CASEA2 = "ab", CASEB2 = "ab";
    private static final String CASEA3 = "aa", CASEB3 = "aa";
    private static final String CASEA4 = "aaaaaaabc", CASEB4 = "aaaaaaacb";
    private static final String CASEA5 = "", CASEB5 = "aa";

    @Test
    public void testCase1() {
        Assert.assertTrue(buddyStrings(CASEA1, CASEB1));
    }

    @Test
    public void testCase2() {
        Assert.assertFalse(buddyStrings(CASEA2, CASEB2));
    }

    @Test
    public void testCase3() {
        Assert.assertTrue(buddyStrings(CASEA3, CASEB3));
    }

    @Test
    public void testCase4() {
        Assert.assertTrue(buddyStrings(CASEA4, CASEB4));
    }

    @Test
    public void testCase5() {
        Assert.assertFalse(buddyStrings(CASEA5, CASEB5));
    }
}
