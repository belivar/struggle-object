package com.YinglishZhi.LeetCode_561;

import java.util.Arrays;

/**
 * 数组拆分1
 *
 * @author LDZ
 * @date 2019-08-02 18:06
 */
public class Solution {

    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res + (i % 2 == 0 ? nums[i] : 0);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 5};
        System.out.println(arrayPairSum(nums));
    }
}
