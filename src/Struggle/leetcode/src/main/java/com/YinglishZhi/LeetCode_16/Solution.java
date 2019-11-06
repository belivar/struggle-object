package com.YinglishZhi.LeetCode_16;

import org.junit.Assert;
import org.junit.Test;

/**
 * 最接近三数之和
 *
 * @author LDZ
 * @date 2019-11-06 16:19
 */
public class Solution {

    public static int threeSumClosest(int[] nums, int target) {
        sort(nums);
        int result = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum > target) {
                    // 和大了 往左移
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    return result;
                }
            }
        }
        return result;
    }

    private static void sort(int[] nums) {
        if (0 == nums.length || 1 == nums.length) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int key = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= key) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= key) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }


    // ========= test case =======
    private static final int[] CASE1 = {-1, 2, 1, 4};

    private static final int CASE1_TARGET = 1;
    private static final int CASE1_RESULT = 2;

    private static final int[] CASE2 = {-1, 0, 1, 2, -1, -4};

    private static final int CASE2_TARGET = 0;
    private static final int CASE2_RESULT = 0;

    @Test
    public void testCase1() {
        Assert.assertEquals(threeSumClosest(CASE1, CASE1_TARGET), CASE1_RESULT);
    }

    @Test
    public void testCase2() {
        Assert.assertEquals(threeSumClosest(CASE2, CASE2_TARGET), CASE2_RESULT);
    }
}
