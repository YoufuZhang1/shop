package com.shop.common.config;

import com.shop.common.constants.AuthConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zyf
 * @date 2023年02月08日0:07时
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    /**
     * 1.浏览器------>A------->B
     * 2.mq------->B
     * 3.微信/支付宝------>A-------->B
     *
     * @param requestTemplate 发起远程调用的请求
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

        //获取请求头信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取请求头中的token数据
        String headerValue = request.getHeader(AuthConstants.AUTHORIZATION_KEY);

        //将请求头向下传递,传递到下一次的请求中
        if (!StringUtils.isEmpty(headerValue) && headerValue.contains(AuthConstants.BEARER_KEY)){
            requestTemplate.header(AuthConstants.AUTHORIZATION_KEY, headerValue);
            return;
        }

        //当是其他程序访问时,比如mq的回调或微信的回调,那么是没有token数据的
        requestTemplate.header(AuthConstants.AUTHORIZATION_KEY,AuthConstants.BEARER_KEY+AuthConstants.THIRD_APP_TOKEN);
    }
}
