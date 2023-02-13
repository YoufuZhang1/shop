package com.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement().disable();
        //授权的请求
        http.authorizeRequests()
                //健康检查的请求全部运行访问
                .antMatchers("/actuator/**")
                //允许所有访问
                .permitAll()
                //其余的其他的请求
                .anyRequest()
                //必须认证后才能访问,登录后才能访问
                .authenticated();
    }
}