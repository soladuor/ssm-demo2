package com.sola.servlet;

import com.sola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserServlet
 */
// @Component
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserService userService;

    public void init() throws ServletException {

        // 因为 servlet 类由 servlet 容器管理，和 spring 容器不互通。
        // 所以需要在 servlet 初始化时填充 spring 容器中的 bean。
        WebApplicationContextUtils.getWebApplicationContext(getServletContext())
            .getAutowireCapableBeanFactory().autowireBean(this);
        // ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        // userService = ac.getBean("userService", UserService.class);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", userService.show());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
