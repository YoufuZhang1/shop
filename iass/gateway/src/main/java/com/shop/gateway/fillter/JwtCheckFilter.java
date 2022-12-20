package com.shop.gateway.fillter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.gateway.Constant.GatewayConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;


/**
 * 网关过滤器
 * 用于检查token
 * @author zyf
 * @date 2022年12月20日20:46时
 */
@Configuration
public class JwtCheckFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器
     *             1. 获取请求路径
     *             2. 是否是白名单(/oauth/token)
     *                 如果是白名单,放行
     *             3. 如果不是白名单,我们需要获取token
     *                 请求头中
     *                     Authorization : bearer token
     *             4. 如果传递了我们放行
     *             5. 如果没有传递,我们返回没有权限信息
     * @param exchange the current server exchange
     * @param chain provides a way to delegate to the next filter
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取请求路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        System.out.println("该请求路径是：" + path);

        //白名单用户放行
        if (GatewayConstant.ALLOW_URLS.contains(path)){ return chain.filter(exchange);}
        
        //校验Authorization
        String headerValue  = request.getHeaders().getFirst(GatewayConstant.AUTHORIZATION);

        if (StringUtils.isNotBlank(headerValue )) {
            //获取请求头的value中的token数据
            String token = headerValue.replaceAll(GatewayConstant.BEARER, "");
            //请求有传递token数据,我们放行,交给微服务中的资源服务器进行校验操作
            if (StringUtils.isNotBlank(token)){
                return chain.filter(exchange);
            }
        }

        //代表没有获取到请求头数据,也不是白名单路径,就代表没有权限访问
        //返回401的未授权的信息
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", HttpStatus.UNAUTHORIZED.value());
        resultMap.put("msg", "未授权");


        try {
            return response.writeWith(
                    Mono.just(
                            response.bufferFactory().wrap(
                                    new ObjectMapper().writeValueAsBytes(resultMap)
                            )
                    )
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
