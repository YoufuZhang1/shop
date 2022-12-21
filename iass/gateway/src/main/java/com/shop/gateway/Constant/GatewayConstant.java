package com.shop.gateway.Constant;

import java.util.Arrays;
import java.util.List;

/**
 *  Gateway常量类
 */
public interface GatewayConstant {

    /**
     * 放行的路径
     */
    List<String> ALLOW_URLS = Arrays.asList("/oauth/token");

    /**
     * 请求头的key
     */
    String AUTHORIZATION = "Authorization";


    /**
     * 携带token的前缀
     */
    String BEARER = "bearer ";

    /**
     * 存入redis的token前缀
     */
    String OAUTH_JWT_PREFIX = "oauth:jwt:";

    /**
     * access_token 后面带的token
     */
    String REDIS_JWT_ACCESS_TOKEN_KEY = "access_token";

    /**
     *   token存活时间
     */
    String REDIS_JWT_EXPIRES_IN_KEY = "expires_in";
}
