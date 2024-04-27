package filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        boolean userFound = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    userFound = true;
                    break;
                }
            }
        }

        if (userFound) {
            // 如果找到了名为 "user" 的 Cookie，则继续执行过滤器链
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 如果没有找到名为 "user" 的 Cookie，则重定向到主页面或其他页面
            res.sendRedirect("../index.html"); // 重定向到主页面
        }
    }

}
