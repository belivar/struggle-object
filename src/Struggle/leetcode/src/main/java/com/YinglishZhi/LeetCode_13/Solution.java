package com.YinglishZhi.LeetCode_13;

/**
 * 罗马数字转整数
 * III 3
 * IV 4
 *
 *
 * @author LDZ
 * @date 2019-07-30 18:55
 */
public class Solution {


    public static int romanToInt(String s) {

        int[] dic = new int['Z' - 'A' + 1];

        dic['I' - 'A'] = 1;
        dic['V' - 'A'] = 5;
        dic['X' - 'A'] = 10;
        dic['L' - 'A'] = 50;
        dic['C' - 'A'] = 100;
        dic['D' - 'A'] = 500;
        dic['M' - 'A'] = 1000;

        char[] sChars = s.toCharArray();
        int res = dic[sChars[0] - 'A'];
        for (int i = 1; i < s.length(); i++) {
            if (dic[sChars[i] - 'A'] > dic[sChars[i - 1] - 'A']) {
                res = res + (dic[sChars[i] - 'A'] - 2 * dic[sChars[i - 1] - 'A']);
            } else {
                res = res + dic[sChars[i] - 'A'];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }
}
