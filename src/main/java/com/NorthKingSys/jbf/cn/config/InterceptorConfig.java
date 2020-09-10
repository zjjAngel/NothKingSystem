package com.NorthKingSys.jbf.cn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig  extends WebMvcConfigurerAdapter {
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE","OPTIONS" };
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器
//        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
//        // 添加拦截请求
//        ir.addPathPatterns("/*");
//        // 添加不拦截的请求
//        ir.excludePathPatterns("/login");

        // 以上三句代码可以使用下面的代替
         registry.addInterceptor(new AdminInterceptor())
                 .addPathPatterns("/*")
                 .excludePathPatterns("/login")
                 .excludePathPatterns("/tologin")
                 .excludePathPatterns("/registry")
                 .excludePathPatterns("/goto")
                 .excludePathPatterns("/verifyCode");
//        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS)
                .maxAge(3600);
    }
}
