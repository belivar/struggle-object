package com.YinglishZhi.LeetCode_1078;

import java.util.ArrayList;
import java.util.List;

/**
 * Bigram 分词
 *
 * @author LDZ
 * @date 2019-07-24 15:23
 */
public class Solution {

    public static String[] findOcurrences(String text, String first, String second) {
        List<String> res = new ArrayList<>();
        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length - 2; i++) {
            if (first.equals(strings[i]) && second.equals(strings[i + 1])) {
                res.add(strings[i + 2]);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
