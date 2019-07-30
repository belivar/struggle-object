package com.YinglishZhi.LeetCode_970;

import java.util.ArrayList;
import java.util.List;

/**
 * 强整数
 *
 * @author LDZ
 * @date 2019-07-29 11:54
 */
public class Solution {

    private static List<Integer> powerfulIntegers(int x, int y, int bound) {

        List<Integer> res = new ArrayList<>();
        int iMax = x == 1 ? 0 : (int) (Math.log(bound) / Math.log(x));

        int jMax = y == 1 ? 0 : (int) (Math.log(bound) / Math.log(y));

        for (int i = 0; i <= iMax; i++) {
            for (int j = 0; j <= jMax; j++) {
                int result = (int) (Math.pow(x, i) + Math.pow(y, j));
                if (result <= bound && !res.contains(result)) {
                    res.add(result);
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = powerfulIntegers(2, 1, 10);
        System.out.println(res);
    }
}
