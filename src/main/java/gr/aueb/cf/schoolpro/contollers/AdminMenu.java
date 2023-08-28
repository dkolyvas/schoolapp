package gr.aueb.cf.schoolpro.contollers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/adminmenu")
public class AdminMenu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("insert");
        String message = "";
        String paramDel = request.getParameter("delete");
        if(param != null && param.equals("true")) message = "Επιτυχής εισαγωγή";
        if(paramDel != null && paramDel.equals("true")) message = "Επιτυχής διαγραφή";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/templates/static/admin/adminmenu.jsp").forward(request, response);

    }


}
