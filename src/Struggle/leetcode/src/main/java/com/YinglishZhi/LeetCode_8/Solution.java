package com.YinglishZhi.LeetCode_8;

/**
 * 字符串转换整数
 *
 * @author LDZ
 * @date 2019-07-30 17:19
 */
public class Solution {

    public static int myAtoi(String str) {

        int len = str.length();

        int result = 0;
        boolean ltZero = false;
        boolean isFirstSub = true;
        boolean isFirstAdd = true;
        boolean isEffect = true;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case ' ':
                    if (isEffect) {
                        break;
                    } else {
                        return ltZero ? -1 * result : result;
                    }
                case '-':
                    if (isFirstSub && isFirstAdd) {
                        isEffect = false;
                        ltZero = true;
                        isFirstSub = false;
                        break;
                    } else {
                        return ltZero ? -1 * result : result;
                    }
                case '+':
                    if (isFirstAdd && isFirstSub) {
                        isEffect = false;
                        ltZero = false;
                        isFirstAdd = false;
                        break;
                    } else {
                        return ltZero ? -1 * result : result;
                    }
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                    isFirstSub = false;
                    isFirstAdd = false;
                    isEffect = false;
                    int sum = result * 10 + c - 48;
                    if (sum < 0 || ((sum - c + 48) / 10) != result) {
                        return ltZero ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                    } else {
                        result = sum;
                    }
                    break;
                default:
                    return ltZero ? -1 * result : result;
            }
        }
        return ltZero ? -1 * result : result;
    }

    public static void main(String[] args) {
//        int res1 = myAtoi("42");
//        int res2 = myAtoi("    -42");
//        int res3 = myAtoi("4193 with words");
//        int res4 = myAtoi("words and 987");
//        int res5 = myAtoi("-91283472332");
//        int res6 = myAtoi("+1");
//        int res7 = myAtoi("+-1");
//        int res8 = myAtoi("  +0 123");
        int res8 = myAtoi("2147483648");
//        System.out.println(res1);
//        System.out.println(res2);
//        System.out.println(res3);
//        System.out.println(res4);
//        System.out.println(res5);
//        System.out.println(res6);
//        System.out.println(res7);
//        System.out.println(res8);
        System.out.println(res8);

    }
}
