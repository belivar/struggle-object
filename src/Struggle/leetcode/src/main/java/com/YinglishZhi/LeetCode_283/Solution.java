package com.YinglishZhi.LeetCode_283;

/**
 * @author LDZ
 * @date 2019-09-08 22:15
 */
public class Solution {

    private static void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        for (int i = nums.length - count; i < nums.length; i++) {
            nums[i] = 0;
        }
        System.out.println(nums);
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1};
        moveZeroes(nums);
    }
}
