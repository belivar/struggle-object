package com.YinglishZhi.LeetCode_697;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组的度
 *
 * @author LDZ
 * @date 2019-08-02 16:56
 */
public class Solution {

    public static int findShortestSubArray(int[] nums) {

//        int[] numCount = new int[50000];
        Map<Integer, Integer> numsCount = new HashMap<>();
        Map<Integer, Integer[]> numPosition = new HashMap<>();

        int degree = 0;
        for (int i = 0; i < nums.length; i++) {
            numsCount.merge(nums[i], 1, Integer::sum);
            if (numsCount.get(nums[i]) == 1) {
                // 第一次
                numPosition.put(nums[i], new Integer[]{i, i});
            } else {
                // 后
                Integer[] position = numPosition.get(nums[i]);
                position[1] = i;
                numPosition.put(nums[i], position);
            }
            degree = Math.max(degree, numsCount.get(nums[i]));
        }
        int res = nums.length;
        for (Map.Entry<Integer, Integer> entry : numsCount.entrySet()) {
            if (entry.getValue() == degree) {
                Integer[] position = numPosition.get(entry.getKey());
                res = Math.min(res, position[1] - position[0]);
            }
        }
        return res + 1;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 1};
        int res = findShortestSubArray(nums);
        System.out.println(res);
    }
}
