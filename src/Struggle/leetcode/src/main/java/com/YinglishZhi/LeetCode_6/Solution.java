package com.YinglishZhi.LeetCode_6;

/**
 * z型
 *
 * @author LDZ
 * @date 2019-07-29 13:35
 */
public class Solution {

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int f = numRows > 2 ? 2 : 1;
        int T = numRows * 2 - f;
        int len = s.length();
        int row = len / (T);
        row = (len % (T) == 0) ? row : row + 1;
        String[][] res = new String[numRows][row * 2];

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int p = i / (T);
            int carry = i % (T);

            if (carry < numRows) {
                // 安排
                res[carry][2 * p] = String.valueOf(c);
            } else {
                int carrry = carry % numRows;
                int pos = numRows - 2 - carrry;
                // 安排
                res[pos][2 * p + 1] = String.valueOf(c);
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < row * 2; j++) {
                String c = res[i][j];
                if (c != null) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = convert("ABCD", 2);
        System.out.println(s);
    }
}
