package com.example.yinglishzhi.listen;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LDZ
 * @date 2019-11-21 18:06
 */
public abstract class AbstractRecordListen {

    private Map<String, List<Method>> consumerMap = initMethodMap();

    private Map<String, List<Method>> initMethodMap() {
        Map<String, List<Method>> result = new HashMap<>();
        Method[] methods = getClass().getMethods();
        System.out.println("------ consumer map --------");
        return result;
    }
}
