package com.YinglishZhi.LintCode_49;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序。
 *
 * @author LDZ
 * @date 2019-01-16
 **/
@Slf4j
public class Solution {

    /**
     * @param chars
     */
    private static void sortLetters(char[] chars) {
        int left = 0;
        int right = chars.length - 1;
        while (left <= right) {
            while (left <= right && isLowerCase(chars[left])) left++;
            while (left <= right && isUpperCase(chars[right])) right--;
            if (left <= right) {
                char ch = chars[left];
                chars[left] = chars[right];
                chars[right] = ch;
            }
        }


    }

    private static void sortLettersV2(char[] chars) {
        int n = chars.length;
        int i = 0;
        for (int j = 0; j < n; j++) {
            if (isLowerCase(chars[j])) {
                char ch = chars[i];
                chars[i] = chars[j];
                chars[j] = ch;
                i++;
            }
        }
    }


    private static boolean isLowerCase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private static boolean isUpperCase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }


    public static void main(String[] args) {

        log.info("我爱杨惠！");
        char[] chars = {'c'};
        sortLetters(chars);

    }
}
