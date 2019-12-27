package com.YinglishZhi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sun.tools.doclint.Entity.sub;

/**
 * @author
 */
public class JavaSE8 {

    private static final List<List<Integer>> LIST = new ArrayList<>();

    static {
        LIST.add(Arrays.asList(1, 2));
        LIST.add(Arrays.asList(3, 4));
        LIST.add(null);
        LIST.add(Arrays.asList(4, 3, 5));
    }


    public static void main(String[] args) {
        List<Long> a = null;
        Long b = null;


        List<Object> collect = Stream.of(a, Collections.singletonList()).filter(Objects::nonNull).flatMap(Collection::stream).filter(Objects::nonNull).
        collect(Collectors.toList());
        System.out.println(collect);
//        List<String> appleVos = Arrays.asList(
//                "apple", "apple",
//                "orange", "orange", "orange",
//                "blueberry",
//                "peach", "peach", "peach", "peach"
//        );
//        Map<String, Long> fruitCount = appleVos.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


    }

    public List<Integer> old() {
        List<Integer> result = new ArrayList<>();
        for (List<Integer> list : LIST) {
            result.addAll(list);
        }
        return result;
    }


}
