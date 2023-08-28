package gr.aueb.cf.schoolpro.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        boolean isUser = false;
        for(Cookie cookie: cookies){
            if(cookie.getName().equals( "JSESSIONID")){
                HttpSession session = request.getSession();
                isUser = (session != null) & session.getAttribute("username") != null;
            }
        }
        if(isUser){
            filterChain.doFilter(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
