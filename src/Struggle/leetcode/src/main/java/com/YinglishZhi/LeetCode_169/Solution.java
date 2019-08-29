package com.YinglishZhi.LeetCode_169;

/**
 * 求众数
 *
 * @author LDZ
 * @date 2019-08-02 16:17
 */
public class Solution {

    public static int majorityElement(int[] nums) {

        int res = 0;
        int nTimes = 0;
        for (int n : nums) {
            if (0 == nTimes) {
                res = n;
                nTimes = 1;
            } else {
                nTimes = nTimes + (res == n ? 1 : -1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 1, 1, 1, 2, 2};
        int res = majorityElement(nums1);
        System.out.println(res);
    }
}
