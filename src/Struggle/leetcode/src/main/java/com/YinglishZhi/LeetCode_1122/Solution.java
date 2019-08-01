package com.YinglishZhi.LeetCode_1122;

public class Solution {

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] mark = new int[1001];

        int[] res = new int[arr1.length];

        for (int value : arr1) {
            mark[value]++;
        }

        int count = 0;
        for (int value : arr2) {
            while (mark[value] > 0) {
                res[count++] = value;
                mark[value]--;
            }
        }

        for (int i = 0; i < mark.length; i++) {
            while (mark[i] > 0) {
                res[count++] = i;
                mark[i]--;
            }
        }

        return res;

    }

    public static void main(String[] args) {

        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};


        int[] res = relativeSortArray(arr1, arr2);
        for (int r : res) {
            System.out.println(r);
        }

    }
}
