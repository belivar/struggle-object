package com.example.yinglishzhi.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/v1")
public class Article {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "memeda";
    }
}
