package com.YinglishZhi.LeetCode_229;

import java.util.ArrayList;
import java.util.List;

/**
 * 球众数
 *
 * @author LDZ
 * @date 2019-08-21 22:36
 */
public class Solution {

    public static List<Integer> majorityElement(int[] nums) {

        List<Integer> result = new ArrayList<>(2);

        if (null == nums || nums.length == 0) {
            return result;
        }

        int candidateA = nums[0];
        int candidateB = nums[0];

        int countA = 0;
        int countB = 0;

        for (int num : nums) {
            if (num == candidateA) {
                countA++;
                continue;
            }

            if (num == candidateB) {
                countB++;
                continue;
            }

            if (countA == 0) {
                candidateA = num;
                countA++;
                continue;
            }

            if (countB == 0) {
                candidateB = num;
                countB++;
                continue;
            }

            countA--;
            countB--;
        }


        result.add(candidateA);
        result.add(candidateB);
        return result;

    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        List<Integer> result = majorityElement(nums);
        System.out.println(result);
    }
}
