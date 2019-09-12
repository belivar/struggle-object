package com.YinglishZhi.LeetCode_977;

public class Solution {

    public static int[] sortedSquares(int[] A) {
        int left = 0;
        int right = A.length - 1;

        int[] res = new int[A.length];
        int count = A.length - 1;
        while (right > left) {
            int n1 = A[right] * A[right];
            int n2 = A[left] * A[left];
            if (n2 > n1) {
                res[count] = n2;
                left++;
            } else {
                res[count] = n1;
                right--;
            }
            count--;
        }
        return res;
    }


    private static int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int partition = partition(nums, start, end);
            quickSort(nums, partition + 1, end);
            quickSort(nums, start, partition - 1);
        }
    }


    private static int partition(int[] nums, int start, int end) {
        int key = nums[start];

        while (start < end) {
            while (start < end && nums[end] >= key) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] < key) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] res = sortedSquares(nums);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
