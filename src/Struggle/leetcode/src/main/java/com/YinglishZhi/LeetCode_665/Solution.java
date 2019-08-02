package com.YinglishZhi.LeetCode_665;

/**
 * 非递减数列
 *
 * @author LDZ
 * @date 2019-08-02 15:46
 */
public class Solution {

    public static boolean checkPossibility(int[] nums) {

        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] nums = {4, 3, 2};
        boolean res = checkPossibility(nums);
        System.out.println(res);
    }
}
