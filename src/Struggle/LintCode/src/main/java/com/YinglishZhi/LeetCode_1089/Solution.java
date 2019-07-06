package com.YinglishZhi.LeetCode_1089;

import java.util.ArrayList;

/**
 * duplicate zeros
 *
 * @author LDZ
 * @date 2019-07-06 23:43
 */
public class Solution {

    public static void duplicateZeros(int[] arr) {
        int size = arr.length;
        int i = 0;
        while (i < size) {
            if (0 == arr[i]) {
                System.arraycopy(arr, i, arr, i + 1, size - i - 1);
                arr[i] = 0;
                i = i + 2;
            } else {
                i++;
            }
        }
        for (int k : arr) {
            System.out.print(k + "-");
        }
    }

    public static void main(String[] args) {
        int[] a = {0, 4, 1, 0, 0, 8, 0, 0, 3};
        duplicateZeros(a);
    }
}
