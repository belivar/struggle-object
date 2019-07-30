package com.YinglishZhi.LeetCode_704;

/**
 * 二分查找
 *
 * @author LDZ
 * @date 2019-07-06 22:57
 */
public class Solution {

    public static int search(int[] nums, int target) {
        int size = nums.length;
        int left = 0;
        int right = size -1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int re = search(nums, 111);
        System.out.println(re);
    }
}
