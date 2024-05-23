package com.soladuor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

// 标记为配置类
@Configuration
// 开启组件扫描【只扫描Controller】
@ComponentScan(basePackages = "com.soladuor.controller")
// 启用MVC配置，解决后续其他问题（view-controller、default-servlet-handler、Jackson消息转换器等23+种问题）
// 相当于 XML 配置中的 <mvc:annotation-driven>
@EnableWebMvc
// 可以通过实现 WebMvcConfigurer 接口的方式配置 MVC 的 API
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
    }

    // 装配 default-servlet-handler，解决静态资源加载问题
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 拦截器需用注入的方式引入
    // @Autowired
    // private XXXInterceptor xxxInterceptor;
    // // 添加拦截器
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     InterceptorRegistration xxxInter = registry.addInterceptor(xxxInterceptor);
    //     xxxInter.addPathPatterns("/user/**", "/file/**")
    //             .excludePathPatterns("/user/login", "/user/signup", "/file/download/**");
    // }

}

