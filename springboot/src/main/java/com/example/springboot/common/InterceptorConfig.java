package com.example.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 功能：
 * 作者：小华
 * 日期： 2023/12/1016:10
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())//配置jwt的拦截器的规则
                .addPathPatterns("/**");
//                .excludePathPatterns("/login");//第二种放行放行方式
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

}