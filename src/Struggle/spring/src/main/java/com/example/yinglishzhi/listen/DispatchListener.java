package com.example.yinglishzhi.listen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LDZ
 * @date 2019-11-21 18:09
 */
@Service("dispatchListener")
public class DispatchListener {

    private ApplicationContext context;

    private Map<String, List<String>> hashServiceMap;

    @Autowired
    public void setContext(ApplicationContext context) {
        System.out.println("--- application context --- ");
        this.context = context;
        hashServiceMap = initHashServiceMap();

    }

    private Map<String, List<String>> initHashServiceMap() {
        Map<String, List<String>> result = new HashMap<>();
        try {
            Map<String, AbstractRecordListen> listenerMap = context.getBeansOfType(AbstractRecordListen.class);
            System.out.println(listenerMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
