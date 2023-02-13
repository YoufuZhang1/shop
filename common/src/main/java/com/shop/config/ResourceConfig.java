package com.shop.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.nio.charset.Charset;

/**
 * @author zyf
 * @date 2023年02月07日23:55时
 */
@Configuration
public class ResourceConfig extends ResourceServerConfigurerAdapter {


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        //加载公钥
        ClassPathResource resource = new ClassPathResource("public.txt");

        //读取到内存中
        String publicKeuStr = FileUtil.readString(resource.getPath(), Charset.defaultCharset());

        //通过公钥对密匙进行校验
        jwtAccessTokenConverter.setVerifierKey(publicKeuStr);

        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        /*
            认证的资源信息
         */
        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement().disable();
        //请求的授权操作
        http.authorizeRequests()
                //匹配的请求地址
                .antMatchers(
                        //Swagger的接口文档的请求
                        "/v2/api-docs",  // swagger  druid ...
                        "/v3/api-docs",
                        "/swagger-resources/configuration/ui",  //用来获取支持的动作
                        "/swagger-resources",                   //用来获取api-docs的URI
                        "/swagger-resources/configuration/security",//安全选项
                        "/webjars/**",
                        "/swagger-ui/**",
                        //数据库连接池的请求
                        "/druid/**",
                        //健康检查的请求
                        "/actuator/**")
                //全部放行
                .permitAll()
                //其余的请求
                .anyRequest()
                //必须认证后才允许访问
                .authenticated();
    }
}
