package com.YinglishZhi.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringUtils {
    public static List<String> String2List(String str) {
        List<String> list = new ArrayList<>();
        String[] strings;

        if (str.contains(",")) {
            strings = str.split(",");
        } else if (str.contains(" ")) {
            strings = str.split(" ");
        } else if (str.contains("、")) {
            strings = str.split("、");
        } else {
            list.add(str);
            return list;
        }
        list = Arrays.stream(strings).collect(Collectors.toList());
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {

        Function<Integer, Integer> function = a -> ((a >> 2) << 2);

        System.out.println(function.apply(17));


    }
}
