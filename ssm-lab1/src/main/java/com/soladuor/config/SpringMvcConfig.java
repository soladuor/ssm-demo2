package com.soladuor.config;

import com.soladuor.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackages = "com.soladuor.controller")
@EnableWebMvc // 装配，解决后续其他问题（view-controller、default-servlet-handler、Jackson消息转换器等23+种问题）
public class SpringMvcConfig implements WebMvcConfigurer {
    // 模板解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".html");
        return resolver;
    }

    // 模板引擎（注入模板解析器）
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    // 配置视图解析器（并注入模板引擎）
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    // 装配视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/test").setViewName("test/test");
    }

    // 装配 default-servlet-handler，解决静态资源加载问题（使用默认的servlet处理静态资源）
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 静态资源加载问题的另一个解决方法（添加资源处理程序），对应注解 mvc:resources
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     // 请求路径，资源路径
    //     registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    //     registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    //     registry.addResourceHandler("/img/**").addResourceLocations("/img/");
    // }

    // 配置上传
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // 设置字符集
        resolver.setDefaultEncoding("UTF-8");
        // 设置单次总文件上传大小（因为单次可以上传多个文件）单位为字节，1MB=1024KB, 1KB=1024B(字节)
        // 注：maxUploadSizePerFile 是每个上传文件的大小限制
        resolver.setMaxUploadSize(5 * 1024 * 1024);
        return resolver;
    }

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration userInter = registry.addInterceptor(userInterceptor);
        userInter.addPathPatterns("/user/**", "/file/**")
            .excludePathPatterns("/user/login", "/user/signup", "/file/download/**");
    }
}
