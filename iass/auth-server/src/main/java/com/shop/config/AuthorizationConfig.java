package com.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * 授权服务器，用于颁发token，授权第三方应用
 * @author zyf
 * @date 2023年02月07日21:51时
 */
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * 授权第三方应用
     *            后台管理系统
     *            小程序
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //后台管理系统的第三方应用配置信息
                .withClient("web")
                .secret(encoder().encode("web-secret"))
                .scopes("all")
                //密码授权
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(21600)
                .redirectUris("https://www.baidu.com")
                .and()
                //小程序的第三方应用配置信息
                .withClient("power")
                .secret(encoder().encode("power-secret"))
                .scopes("all")
                //客户端授权,一经授权,永久有效
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(Integer.MAX_VALUE)
                .redirectUris("https://www.baidu.com") ;

    }

    /**
     通过私钥来颁发token
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        //获取私钥,将私钥加载到内存中
        ClassPathResource resource = new ClassPathResource("sz2205.jks");

        //创建密匙工厂
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(resource, "sz2205".toCharArray());
        KeyPair keyPair = factory.getKeyPair("sz2205");

        //通过私钥来颁发token
        jwtAccessTokenConverter.setKeyPair(keyPair);

        return jwtAccessTokenConverter;
    }


    /**
     在SpringSecurity的配置类中进行注入
     */
    @Autowired
    private AuthenticationManager authenticationManager;


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //使用jwt的方式来存储token数据
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager);
    }
}
