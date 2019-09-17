package com.YinglishZhi.LeetCode_1185;

import java.util.HashMap;
import java.util.Map;

/**
 * 一周中的第几天
 *
 * @author LDZ
 * @date 2019-09-13 14:57
 */
public class Solution {


    private static final Map<Integer, String> WEEK = new HashMap<Integer, String>() {{
        put(1, "Monday");
        put(2, "Tuesday");
        put(3, "Wednesday");
        put(4, "Thursday");
        put(5, "Friday");
        put(6, "Saturday");
        put(0, "Sunday");
    }};

    private static final int BEGIN_YEAR = 1970;
    private static final int BEGIN_MONTH = 1;
    private static final int BEGIN_DAY = 1;
    private static final int BEGIN_WEEK = 4;
    private static final int[] MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static String dayOfTheWeek(int day, int month, int year) {
        if (year < 1971 || year > 2100) {
            return null;
        }

        int days = 0;

        for (int i = 1971; i < year; ++i) {
            days += dayOfYear(i);
        }

        for (int i = 1; i < month; i++) {
            if (i == 2 && isLeapYear(year)) {
                days += 29;
            } else {
                days += MONTH[i - 1];
            }
        }

        days += day -1;
        System.out.println(days);
        days = days % 7;
        return WEEK.get((days + BEGIN_WEEK) % 7);
    }


    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static int dayOfYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    public static void main(String[] args) {
        String weel = dayOfTheWeek(31, 8, 2019);
        System.out.println(weel);

        String weel1 = dayOfTheWeek(18, 7, 1999);
        System.out.println(weel1);


    }
}
