package com.YinglishZhi.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LDZ
 * @date 2019-11-09 23:03
 */
public class HeapOutOfMemory {


    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        while (true) {
            objects.add(new Object());
        }
    }
}
