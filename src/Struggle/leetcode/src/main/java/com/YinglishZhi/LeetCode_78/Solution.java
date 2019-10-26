package com.YinglishZhi.LeetCode_78;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 子集
 *
 * @author LDZ
 * @date 2019-10-26 13:34
 */
public class Solution {

    public static List<List<Integer>> subsets(int[] nums) {
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        int N = 1 << size;
        for (int i = 0; i < N; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (((i >> j) & 1) == 1) {
                    tmp.add(nums[j]);
                }
            }
            result.add(tmp);
        }
        return result;
    }

    public static List<List<Integer>> subsets1(int[] nums) {
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (0 == size) {
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size + 1; i++) {
            dfs(nums, 0, i, stack, result);
        }
        return result;
    }

    private static void dfs(int[] nums, int start, int depth, Stack<Integer> temp, List<List<Integer>> result) {
        if (depth == temp.size()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, i + 1, depth, temp, result);
            temp.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        subsets1(nums);
        List<List<Integer>> result = subsets1(nums);
        for (List<Integer> re : result) {
            System.out.println(re);
        }
    }
}
