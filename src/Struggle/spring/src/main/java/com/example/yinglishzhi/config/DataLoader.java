package com.example.yinglishzhi.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author LDZ
 * @date 2019-09-17 11:05
 */
@Configuration
public class DataLoader implements ApplicationListener<ApplicationContextEvent> {

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        System.out.println(6666);
    }
}
