package com.YinglishZhi.LeetCode_1013;

/**
 * 将数组分成和相等的三个部分
 *
 * @author LDZ
 * @date 2019-08-02 18:47
 */
public class Solution {

    public static boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int num : A) {
            sum += num;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int block = sum / 3;
        int temp = 0;
        int j = 0;
        for (int value : A) {
            if (temp == block) {
                j++;
                temp = 0;
            }
            temp += value;
            if (j == 2) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] A = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        int[] A = {0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1};
        boolean res = canThreePartsEqualSum(A);
        System.out.println(res);
    }
}
