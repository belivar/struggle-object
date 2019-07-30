package com.YinglishZhi.LeetCode_717;

/**
 * is one bit character
 *
 * @author LDZ
 * @date 2019-07-06 23:22
 */
public class Solution {
    public static boolean isOneBitCharacter(int[] bits) {


        int size = bits.length;

        int left = 0;

        while (left < size-1) {
            if (1 == bits[left]) {
                left = left + 2;
            } else if (0 == bits[left]) {
                left = left + 1;
            }
        }
        return left == size - 1;
    }


    public static void main(String[] args) {
        int[] bits = {1, 1,1, 0};
        System.out.println(isOneBitCharacter(bits));
    }
}
