package com.YinglishZhi.LeetCode_581;

import java.util.Stack;

/**
 * 最短无序连续子数组
 *
 * @author LDZ
 * @date 2019-08-21 22:13
 */
public class Solution {

    public static int findUnsortedSubarray(int[] nums) {

        Stack<Integer> stack = new Stack<>();

        int l = nums.length;
        int r = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                l = Math.min(l, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                r = Math.max(r, stack.pop());
            }
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] nums = {2, 1};
        int result = findUnsortedSubarray(nums);
        System.out.println(result);
    }
}
