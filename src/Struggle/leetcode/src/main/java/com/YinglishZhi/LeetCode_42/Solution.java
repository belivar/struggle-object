package com.YinglishZhi.LeetCode_42;

/**
 * 接水
 *
 * @author LDZ
 * @date 2019-09-17 14:58
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        int result = trap(nums);
        System.out.println(result);
    }

    private static int trap(int[] height) {

        int length = height.length;

        if (0 == length) {
            return 0;
        }

        int[] left = new int[length];
        int[] right = new int[length];

        left[0] = height[0];
        for (int i = 1; i < length; i++) {
            left[i] = Math.max(height[i], left[i - 1]);
        }
        right[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i + 1]);
        }

        int result = 0;
        for (int i = 0; i < length; i++) {
            result = result + Math.min(left[i], right[i]) - height[i];
        }
        return result;
    }


    private static int trap2(int[] height) {
        int maxL = 0, maxR = 0;
        int s = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            if (height[i] < height[j]) {
                if (height[i] > maxL)
                    maxL = height[i];
                else
                    s += maxL - height[i];
                i++;
            } else {
                if (height[j] > maxR)
                    maxR = height[j];
                else
                    s += maxR - height[j];
                j--;
            }
        }
        return s;
    }

}
