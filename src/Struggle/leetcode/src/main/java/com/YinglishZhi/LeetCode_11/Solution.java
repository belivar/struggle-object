package com.YinglishZhi.LeetCode_11;

import com.YinglishZhi.util.Constant;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author LDZ
 * @date 2019-11-05 12:07
 */
public class Solution {

    private static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int result = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                result = Math.max(result, Math.min(height[i], height[j]) * (j - i));
                i++;
            } else {
                result = Math.max(result, Math.min(height[i], height[j]) * (j - i));
                j--;
            }
        }
        return result;
    }


    // ======== test case =======
    private static final int[] HEIGHT = {1, 8, 6, 2, 5, 4, 8, 3, 7};

    @Test
    public void testCase1() {
        int result = maxArea(HEIGHT);
        Assert.assertEquals(result, 49);
    }

    @Test
    public void testCaseNull() {
        Assert.assertEquals(maxArea(Constant.EMPTY_INT_ARRAY), 0);
    }

}
