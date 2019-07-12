package com.YinglishZhi.LeetCode_575;

import java.util.ArrayList;
import java.util.List;

/**
 * 分糖果
 *
 * @author LDZ
 * @date 2019-07-12 20:51
 */
public class Solution {

    public static int distributeCandies(int[] candies) {
        int size = candies.length;
        List<Integer> list = new ArrayList<>();
        for (int cur : candies) {
            if (!list.contains(cur)) {
                list.add(cur);
            }
            if (size / 2 <= list.size()) {
                break;
            }
        }
        return list.size();
    }

    private static int indexOf(int cur, int[] result) {
        for (int i = 0; i < result.length; i++) {
            if (cur == result[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] candies = {0,0,0,4};
        int result = distributeCandies(candies);
        System.out.println(result);
    }
}
