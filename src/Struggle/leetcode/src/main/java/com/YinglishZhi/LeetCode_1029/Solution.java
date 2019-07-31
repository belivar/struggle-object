package com.YinglishZhi.LeetCode_1029;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 两地调度
 *
 * @author LDZ
 * @date 2019-07-31 17:13
 */
public class Solution {

    public static int twoCitySchedCost(int[][] costs) {

        // 按两值 差值排序
        Arrays.sort(costs, Comparator.comparingInt(a -> a[0] - a[1]));

        int res = 0;
        // 前 N个是 A地 剩下都是B

        for (int i = 0; i < costs.length; i++) {
            if (i < costs.length / 2) {
                res += costs[i][0];
            } else {
                res += costs[i][1];
            }
        }
        return res;
    }

    private static int twoCitySchedCostV2(int[][] costs) {
        int res = 0;
        int[] diff = new int[costs.length];
        for (int i = 0; i < costs.length; i++) {
            res = res + costs[i][0] + costs[i][1];
            diff[i] = costs[i][0] - costs[i][1];
        }
        Arrays.sort(diff);
        for (int i = 0; i < costs.length / 2; i++) {
            // 加上最小差值的两数 ( + A - B)
            // 减去最大差值的两数 ( - A + B)
            res = res + diff[i] - diff[costs.length - 1 - i];
        }
        return res / 2;
    }

    public static void main(String[] args) {
        int[][] costs = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        System.out.println(twoCitySchedCostV2(costs));
    }
}
