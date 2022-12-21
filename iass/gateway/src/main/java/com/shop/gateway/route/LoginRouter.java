package com.shop.gateway.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.gateway.Constant.GatewayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * 网关过滤路由操作
 * @author zyf
 * @date 2022年12月21日21:33时
 */
public class LoginRouter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                //当请求地址是/oauth/token时,转发到auth-server微服务
                .route("auth-server",r->r.path("/oauth/token")
                        .filters(f -> f.modifyRequestBody(String.class,String.class,((serverWebExchange, s) -> {
                                    //s为响应回来的token数据
                                    //{access_token:xxx,expires_in:xxx}
                                    //将token和它的过期时间存入到Redis中,进行保存和校验操作
                                    JSONObject jsonObject  = JSON.parseObject(s);

                                    //正常返回了token数据
                                    if (jsonObject.containsKey(GatewayConstant.REDIS_JWT_ACCESS_TOKEN_KEY)
                                            && jsonObject.containsKey(GatewayConstant.REDIS_JWT_EXPIRES_IN_KEY)){

                                        //获取token
                                        String token = jsonObject.getString(GatewayConstant.REDIS_JWT_ACCESS_TOKEN_KEY);

                                        //获取过期时间
                                        Integer expiresIn = jsonObject.getInteger(GatewayConstant.REDIS_JWT_EXPIRES_IN_KEY);

                                        //获取并存入redis中
                                        redisTemplate.opsForValue().set(
                                                GatewayConstant.OAUTH_JWT_PREFIX,
                                                "",
                                                expiresIn,
                                                TimeUnit.SECONDS
                                               );
                                    }
                                    return Mono.just(s);
                                })))
                        .uri("lb://auth-server")
                )
                 .build();
    }
}
