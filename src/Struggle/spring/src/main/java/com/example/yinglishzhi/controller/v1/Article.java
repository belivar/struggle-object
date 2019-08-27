package com.example.yinglishzhi.controller.v1;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article/v1")
public class Article {


    @Bean
    private Map<String, Integer> integer1() {
        return new HashMap<String, Integer>() {{
            put("1", 1);
        }};
    }

    @Resource
    Map<String, Integer> integer1;

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        System.out.println(integer1);
        return "aa";


    }
}
