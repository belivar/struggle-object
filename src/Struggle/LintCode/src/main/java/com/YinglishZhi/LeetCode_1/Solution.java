package com.YinglishZhi.LeetCode_1;

/**
 * 两数之和
 *
 * @author LDZ
 * @date 2019-07-30 10:07
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int indexArrayMax = Integer.MAX_VALUE - 1;
        int[] indexArrays = new int[indexArrayMax + 1];
        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            if (indexArrays[diff & indexArrayMax] != 0) {
                return new int[]{indexArrays[indexArrayMax & diff] - 1, i};
            }
            indexArrays[nums[i] & indexArrayMax] = i + 1;
        }
        return null;
    }
}
