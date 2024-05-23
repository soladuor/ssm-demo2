package com.soladuor.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soladuor.utils.result.JSONResult;
import com.soladuor.utils.result.ResultCodeEnum;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserInterceptor implements HandlerInterceptor {


    /*
    一、拦截器和过滤器的区别
        1. 过滤器（属于web服务器组件（Tomcat））
            作用：过滤器主要用于过滤Servlet请求
            执行时机（两处）：① Servlet前 ② Servlet后
        2. 拦截器（属于springmvc框架）
            作用：拦截Controller请求
            执行时机（三处）：
                1. 执行 DispatcherServlet 后，Controller 前
                2. 执行 Controller 后，DispatcherServlet 前
                3. 执行 DispatcherServlet 之后【渲染视图之后】
                浏览器发起请求 ->                   DispatcherServlet -> 拦截器(preHandle) -> Controller
                响应 <- 拦截器(afterCompletion) <- DispatcherServlet <- 拦截器(postHandle) <-
    二、拦截器【SpringMVC可以使用拦截器拦截Controller请求，可以自定义拦截器实现特定功能】
        实现方式：
            1. 实现 HandlerInterceptor 接口【推荐】
            2. 继承 HandlerInterceptorAdapter 适配器类【快弃用了】
            注：继承或实现后要在xml配置文件中将这个bean装配到 mvc:interceptors 标签内
        拦截器的三个方法：
            1. preHandle()：在业务处理器（控制器）处理请求之前调用，可以做一些权限的校验
                注：返回true为放行，即调用控制器方法；返回false表示拦截，即不调用控制器方法
            2. postHandle()：控制器方法执行之后，渲染视图之前执行，可以对ModelAndView中的模型进行处理
            3. afterCompletion()：处理完视图和模型数据，渲染视图完毕之后执行，可以做一下资源清理的操作

    三、多个拦截器的执行顺序以及 preHandle 返回值问题
        ①若每个拦截器的 preHandle()都返回 true
            此时多个拦截器的执行顺序和拦截器在SpringMVC的配置文件的配置顺序有关：
                preHandle()会按照配置的顺序执行（因为底层是一个正序的for循环），
                而postHandle()和afterCompletion()会按照配置的反序执行（因为底层是一个倒序的for循环）
        ②若某个拦截器的 preHandle()返回了 false
            preHandle()返回false的拦截器和它之前的拦截器的 preHandle()都会执行，
            postHandle()都不执行，
            preHandle()返回false的拦截器之前的拦截器的 afterCompletion()会执行
            注：也就是说，如果第一个拦截器的preHandle返回false，那程序终止，什么都不返回
        注：看源码的话，可以去DispatcherServlet中按Ctrl+F搜索 preHandle

     */

    /**
     * Controller之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        System.out.println("--> preHandle()：在业务处理器处理请求之前调用");
        HttpSession session = request.getSession();
        System.out.println("session = " + session);
        System.out.println("request.getSession().getId() = " + request.getSession().getId());
        Object user = session.getAttribute("user");
        System.out.println(user);
        if (user == null) {
            response.setContentType("application/json;charset=utf-8");
            JSONResult unauthorized = JSONResult.build(ResultCodeEnum.UNAUTHORIZED, "登录失效");
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(unauthorized));
            return false;
        }
        return true;
    }

    /**
     * Controller之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("--> postHandle()：控制器方法执行之后，渲染视图之前执行");
    }

    /**
     * DispatcherServlet之后【渲染视图之后】
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        System.out.println("--> afterCompletion()：处理完视图和模型数据，渲染视图完毕之后执行");
    }

}
