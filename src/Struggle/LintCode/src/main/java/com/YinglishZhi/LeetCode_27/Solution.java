package com.YinglishZhi.LeetCode_27;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 移除元素
 *
 * @author LDZ
 * @date 2019-07-14 20:44
 */
public class Solution {

    public static int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (val != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
//        int[] nums = {0,1,2,2,3,0,4,2};
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int r = removeElement(nums, val);
        System.out.println(r);
        for (int i : nums) {
            System.out.print(i);
        }
    }
}
