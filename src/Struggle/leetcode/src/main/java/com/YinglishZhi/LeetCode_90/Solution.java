package com.YinglishZhi.LeetCode_90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 子集 二
 *
 * @author LDZ
 * @date 2019-10-26 14:01
 */
public class Solution {

    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        int size = 1 << length;

        for (int i = 0; i < size; i++) {
            List<Integer> temp = new ArrayList<>();
            boolean illegal = false;
            for (int j = 0; j < length; j++) {
                if (((i >> j) & 1) == 1) {
                    if (j > 0 && nums[j] == nums[j - 1] && (i >> (j - 1) & 1) == 0) {
                        illegal = true;
                        break;
                    } else {
                        temp.add(nums[j]);
                    }
                }
            }
            if (!illegal) {
                result.add(temp);
            }
        }
        return result;
    }


    public static List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int size = nums.length;
        if (0 == size) {
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size + 1; i++) {
            dfs(nums, 0, i, stack, result);
        }
        return result;
    }

    public static void dfs(int[] nums, int start, int depth, Stack<Integer> temp, List<List<Integer>> result) {
        if (depth == temp.size()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 只取第一个 后边的如果相等 就干掉
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            dfs(nums, i + 1, depth, temp, result);
            temp.pop();
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = subsetsWithDup1(nums);
        for (List<Integer> re : result) {
            System.out.println(re);
        }
    }
}
