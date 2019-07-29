package com.YinglishZhi.LeetCode_66;

/**
 * 加一
 *
 * @author LDZ
 * @date 2019-07-29 13:12
 */
public class Solution {

    public static int[] plusOne(int[] digits) {
        return plusOne(digits, 1);
    }

    private static int[] plusOne(int[] digits, int i) {
        int size = digits.length;
        if (digits[size - i] == 9) {
            digits[size - i] = 0;
            if (i + 1 <= size) {
                return plusOne(digits, i + 1);
            } else {
                int[] dig = new int[size + 1];
                System.arraycopy(digits, 0, dig, 1, size);
                dig[0] = 0;
                return plusOne(dig, i + 1);
            }
        } else {
            digits[size - i] = digits[size - i] + 1;
            return digits;
        }
    }

    public static void main(String[] args) {
        int[] nums = {9,9};

        int[] res = plusOne(nums);
        for (int i : res) {
            System.out.println(i);
        }

    }
}
