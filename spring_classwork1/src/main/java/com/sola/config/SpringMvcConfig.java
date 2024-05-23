package com.sola.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 标记为配置类
@Configuration
// 开启组件扫描【只扫描Controller】
@ComponentScan(basePackages = "com.sola.servlet")
// 启用MVC配置，解决后续其他问题（view-controller、default-servlet-handler、Jackson消息转换器等23+种问题）
// 相当于 XML 配置中的 <mvc:annotation-driven>
@EnableWebMvc
// 可以通过实现 WebMvcConfigurer 接口的方式配置 MVC 的 API
public class SpringMvcConfig implements WebMvcConfigurer {
    // idea 可以用 Ctrl+O 的方式查看可以实现的接口

    // 装配 default-servlet-handler，解决静态资源加载问题
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

/*
    // 拦截器需用注入的方式引入
    @Autowired
    private XXXInterceptor xxxInterceptor;
    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration xxxInter = registry.addInterceptor(xxxInterceptor);
        xxxInter.addPathPatterns("/user/**", "/file/**")
                .excludePathPatterns("/user/login", "/user/signup", "/file/download/**");
    }
     */

    // 装配视图控制器，对应标签 mvc:view-controller
    // @Override
    // public void addViewControllers(ViewControllerRegistry registry) {
    //     registry.addViewController("/").setViewName("index");
    //     // 如果需要装配多个，可继续往下写，例如
    //     // registry.addViewController("/test").setViewName("test/test");
    // }

}

