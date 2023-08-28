package gr.aueb.cf.schoolpro.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean isAdmin = false;

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    HttpSession session = request.getSession(false);
                        isAdmin = (session != null) && (session.getAttribute("admin") != null);
                }
            }
        }
        if( isAdmin){
            filterChain.doFilter(request, response);
        } else{
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }
}
