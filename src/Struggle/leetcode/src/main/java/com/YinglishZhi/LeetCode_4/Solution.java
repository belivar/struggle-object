package com.YinglishZhi.LeetCode_4;

/**
 * 寻找中位数
 *
 * @author LDZ
 * @date 2019-07-16 20:38
 */
public class Solution {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = (len1 + len2) / 2 + 1;
        int[] result = new int[len];
        int i = 0;
        int j = 0;

        int k = 0;
        while (i < len1 && j < len2 && k < len) {
            if (nums1[i] < nums2[j]) {
                result[k] = nums1[i];
                i++;
            } else {
                result[k] = nums2[j];
                j++;
            }
            k++;
        }
        while (i < len1 && k < len) {
            result[k] = nums1[i];
            i++;
            k++;
        }
        while (j < len2 && k < len) {
            result[k] = nums2[j];
            j++;
            k++;
        }
        if ((len1 + len2) % 2 == 1) {
            return result[len - 1];
        } else {
            return (result[len - 1] + result[len - 2]) / 2d;
        }
    }

    public static void main(String[] args) {


        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
