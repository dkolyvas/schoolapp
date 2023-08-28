package gr.aueb.cf.schoolpro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session.getAttribute("username") != null) session.removeAttribute("username");
        if(session.getAttribute("admin") != null) session.removeAttribute("admin");
        if(session.getAttribute("usertype") != null) session.removeAttribute("usertype");
        if(session.getAttribute("userdetails") != null) session.removeAttribute("usesrdetails");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
