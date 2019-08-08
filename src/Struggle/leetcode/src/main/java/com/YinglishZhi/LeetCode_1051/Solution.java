package com.YinglishZhi.LeetCode_1051;

import java.util.Arrays;

/**
 * 高度检查器
 *
 * @author LDZ
 * @date 2019-08-02 18:30
 */
public class Solution {

    public static int heightChecker(int[] heights) {

        int[] heightsSorted = new int[heights.length];

        System.arraycopy(heights, 0, heightsSorted, 0, heights.length);
        Arrays.sort(heightsSorted);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heightsSorted[i] != heights[i]) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 4, 2, 1, 3};
        System.out.println(heightChecker(nums));
    }
}
