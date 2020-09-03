package com.NorthKingSys.jbf.cn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器
//        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
//        // 添加拦截请求
//        ir.addPathPatterns("/*");
//        // 添加不拦截的请求
//        ir.excludePathPatterns("/login");

        // 以上三句代码可以使用下面的代替
         registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/*").excludePathPatterns("/login").excludePathPatterns("/tologin").excludePathPatterns("/registry").excludePathPatterns("/goto").excludePathPatterns("*.html");
    }
}
