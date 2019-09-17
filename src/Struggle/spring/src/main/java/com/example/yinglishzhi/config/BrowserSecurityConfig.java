package com.example.yinglishzhi.config;

import com.example.yinglishzhi.service.api.IUserDetailsService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig {

//    private final IUserDetailsService userDetailsService;
//
//    /**
//     * 通过配置类构造函数注入 UserDetailsService
//     */
//    @Autowired
//    public BrowserSecurityConfig(IUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    /**
//     * 在配置类中声明 加密编码器
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


}
