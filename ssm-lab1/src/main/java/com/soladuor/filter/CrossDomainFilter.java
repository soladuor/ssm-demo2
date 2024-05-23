package com.soladuor.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CrossDomainFilter", urlPatterns = "/*")
public class CrossDomainFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 解决跨域问题，允许所有域名访问
        // resp.setHeader("Access-Control-Allow-Origin", "*");
        // 动态获取请求客户端请求的域，不用*的原因是带cookie的请求不支持*号
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
        // 设置响应头信息以允许跨域访问
        // resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        // 指定允许跨域的方法， * 代表所有
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        // 预检命令的缓存，如果不缓存每次会发送两次请求
        resp.setHeader("Access-Control-Max-Age", "3600");
        // 带cookie请求需要加上这个字段，并设置为true
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);
    }
}
