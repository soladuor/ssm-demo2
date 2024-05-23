package com.soladuor;

import com.soladuor.controller.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration userInter = registry.addInterceptor(userInterceptor);
        userInter.addPathPatterns("/user/**", "/file/**")
            .excludePathPatterns("/user/login", "/user/signup", "/file/download/**");
    }
}
