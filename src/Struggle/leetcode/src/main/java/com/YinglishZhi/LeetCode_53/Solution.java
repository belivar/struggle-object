package com.YinglishZhi.LeetCode_53;

/**
 * 最大子序和
 *
 * @author LDZ
 * @date 2019-08-02 17:49
 */
public class Solution {


    public static int maxSubArray(int[] nums) {

        if (0 == nums.length) {
            return 0;
        }

        int max = nums[0];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    private static int maxSubArrayDP(int[] nums) {
        if (0 == nums.length) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum = Math.max(num, sum + num);
            res = Math.max(res, sum);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArrayDP(nums));
    }

}
