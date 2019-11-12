package com.YinglishZhi.Interview_1;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Test;

/**
 * 环形输出 二维数组
 * 1  2  3  4  5
 * 6  7  8  9  10
 * 11 12 13 14 15
 * 16 17 18 19 20
 * <p>
 * >> 1 2 3 4 5 10 15
 *
 * @author LDZ
 * @date 2019-11-12 10:25
 */
public class Solution {


    private String travel(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        travel(arr, 0, sb);
        return sb.toString();
    }


    private void travel(int[][] arr, int level, StringBuilder sb) {

        int row = arr.length;
        int column = arr[0].length;
        // 干到底了
        if (row - level * 2 == 0 || column - level * 2 == 0) {
            return;
        }

        // 剩一行
        if (row - level * 2 == 1) {
            for (int i = level; i < column - level; i++) {
                sb.append(arr[level][i]);
            }
            return;
        }

        if (column - level * 2 == 1) {
            for (int i = level; i < row - level; i++) {
                sb.append(arr[i][level]);
            }
            return;
        }

        // 顶边
        for (int i = level; i < column - level; i++) {

            sb.append(arr[level][i]);
        }

        // 右侧边
        for (int i = level + 1; i < row - 1 - level; i++) {
            sb.append(arr[i][column - 1 - level]);
        }

        // 底边
        for (int i = column - 1 - level; i >= level; i--) {
            sb.append(arr[row - 1 - level][i]);
        }

        // 左侧边
        for (int i = row - 2 - level; i >= level + 1; i--) {
            sb.append(arr[i][level]);
        }

        travel(arr, level + 1, sb);
    }


    private static final int[][] TEST_ARRAY1 = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {20, 27, 28, 29, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20}
    };

    private static final String CASE1 = "12345101015201918171611206789291413122728";

    @Test
    public void testCase1() {
        Assert.assertEquals(travel(TEST_ARRAY1), CASE1);
    }
}

